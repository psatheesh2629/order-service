package com.infy.ms.order.repository;

import com.infy.ms.order.model.Orders;

public interface OrderRepository  {
	
	Orders save(Orders order);
	Orders getOrder(Integer orderId);
	boolean updateOrder(Integer orderId, Boolean paymentStatus);
	

}
