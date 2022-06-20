package com.infy.ms.order.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infy.ms.order.model.Orders;

@Repository
public interface OrderDataRepository extends CrudRepository<Orders, Integer> {

}
