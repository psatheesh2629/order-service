package com.infy.ms.order.service;

import com.infy.ms.order.model.Orders;

public interface OrderService {
	
	Orders save(Orders order);
	Orders getOrder(Integer orderId);
	boolean updateOrder(Integer orderId, Boolean paymentStatus);

}
