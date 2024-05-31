package com.product.ecommerce.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.ecommerce.model.entity.Order;
import com.product.ecommerce.model.entity.OrderStatus;
import com.product.ecommerce.model.entity.PaymentStatus;
import com.product.ecommerce.model.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

	Optional<Order> findByOrderId(String orderId);

	void deleteByOrderId(String orderId);
	
    List<Order> findByUserId(Long userId, Pageable pageable);
	
	@Query("select os from OrderStatus os where status = ?1")
	Optional<OrderStatus> findByOrderStatus(String orderStatus);
	
	@Query("select os from PaymentStatus os where status = ?1")
	Optional<PaymentStatus> findByPaymentStatus(String paymentStatus);
	
}