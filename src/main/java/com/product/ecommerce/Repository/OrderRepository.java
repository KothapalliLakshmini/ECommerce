package com.product.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.ecommerce.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

	Optional<Order> findByOrderId(String orderId);

	void deleteByOrderId(String orderId);
	
}

