package com.infy.ms.order.resource;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infy.ms.order.loadbalancing.LoadBalancingConfig;
import com.infy.ms.order.model.Orders;
import com.infy.ms.order.model.Payment;
import com.infy.ms.order.model.Product;
import com.infy.ms.order.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.vavr.concurrent.Future;

@RestController
@RequestMapping("/order")
@LoadBalancerClient(name = "payment-service", configuration = LoadBalancingConfig.class)
public class OrderResource {

	/***
	 * RestTemplate
	 * 
	 */
	@Autowired
	private OrderService orderService;
	@Autowired
	private DiscoveryClient client;
	@Autowired
	private RestTemplate paymentRestTemplate;

	@RequestMapping(value = "/getTransaction/{productName}/{productQuantity}", method = RequestMethod.GET)
	public Orders generateOrder(@PathVariable String productName, @PathVariable Integer productQuantity) {
		Orders order = null;
		HashMap<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("productName", productName);
		ResponseEntity<Product> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:9000/product/getProductByName/{productName}", Product.class, uriVariables);
		Product p = responseEntity.getBody();
		if (null != p) {
			int totalPrice = (p.getProductPrice() * productQuantity);
			int orderId = new Random().nextInt();
			order = new Orders(orderId, productName, productQuantity, totalPrice, Boolean.FALSE);
		}
		return orderService.save(order);
	}

	@RequestMapping(value = "/deliver/{orderId}", method = RequestMethod.GET)
	@CircuitBreaker(name = "payment-service", fallbackMethod = "paymentServiceFallbackMethod")
	public void deliverProducts(@PathVariable Integer orderId) {
		Orders order = orderService.getOrder(orderId);
		if (null != order && order.getPaymentStatus()) {
			System.out.println("Deliver the product");
		} else if (null != order) {
			Payment payment = new Payment(new Random().nextInt(), new Date(), orderId, order.getTotalPrice());
			HttpEntity<Payment> request = new HttpEntity<Payment>(payment);
			String paymentUrl = client.getInstances("payment-service").get(0).getUri().toString();
			Future<String> Futureresponse = updatePaymentStatus(request);
			System.out.println(Futureresponse.get());
			orderService.updateOrder(orderId, Boolean.TRUE);
		}
	}

	@PostMapping(value = "/saveOrder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> saveOrder(@RequestBody Orders order) {
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(order).getOrderId());
	}

	public void paymentServiceFallbackMethod(Integer i, Throwable t) {
		System.out.println(i);
		System.out.println(t.getMessage());
		System.out.println("In Fallback Method");
	}

	public Future<String> updatePaymentStatus(HttpEntity<Payment> request) {
		return Future.of(
				() -> paymentRestTemplate.postForObject("http://payment-service/payment/save", request, String.class));
	}

}
