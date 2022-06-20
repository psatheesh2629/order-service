package com.infy.ms.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.ms.order.model.Orders;
import com.infy.ms.order.repository.OrderDataRepository;
import com.infy.ms.order.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderDataRepository orderDataRepository;

	@Override
	public Orders save(Orders order) {
		return orderDataRepository.save(order);
	}

	@Override
	public Orders getOrder(Integer orderId) {
		return orderDataRepository.findById(orderId).get();
	}

	@Override
	public boolean updateOrder(Integer orderId, Boolean paymentStatus) {
		// TODO Auto-generated method stub
		return orderRepository.updateOrder(orderId, paymentStatus);
	}

	

}
