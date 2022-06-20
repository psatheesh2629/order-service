package com.infy.ms.order.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.ms.order.model.Orders;

@Repository
public class OrderRepositoryImpl implements OrderRepository{
	
	
	
	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional
	public Orders save(Orders order) {
		Orders ord = null;
		try {
			if(null != order) {
				ord =  entityManager.merge(order);
			}
		}catch (Exception e) {
			
		}
		return ord;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Orders getOrder(Integer orderId) {
		Query query = entityManager.createQuery("from Orders where orderId=:orderId");
		query.setParameter("orderId", orderId);
		List<Orders> orders = query.getResultList();
		if(null != orders && orders.size()>0) {
			return orders.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public boolean updateOrder(Integer orderId, Boolean paymentStatus) {
		Query query = entityManager.createQuery("update Orders set paymentStatus=:paymentStatus where orderId=:orderId");
		query.setParameter("orderId", orderId);
		query.setParameter("paymentStatus", paymentStatus);
		int result = query.executeUpdate();
		if(result>0) {
			return true;
		}
		return false;
		
	}

	

}
