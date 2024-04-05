package com.product.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.ecommerce.Repository.OrderRepository;
import com.product.ecommerce.model.Order;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository OrderRepository;
	
	public ResponseEntity<List<Order>> getAllOrders(){
		List<Order> Orders = OrderRepository.findAll();
		return new ResponseEntity<>(Orders, HttpStatus.OK);
	}
	
	public ResponseEntity<Order> getOrderById(String OrderId){
		Optional<Order> Orders = OrderRepository.findById(OrderId);
		return new ResponseEntity<>(Orders.get(), HttpStatus.OK);
	}

	public ResponseEntity<String> postOrder(Order Order) {
		Order savedOrder = OrderRepository.save(Order);
		
		return new ResponseEntity<>("Order created with Id " + savedOrder.getOrderId(), HttpStatus.OK) ;
	}

	public ResponseEntity<String> putOrder(Order Order) {
		
		Order savedOrder = OrderRepository.save(Order);	
		return new ResponseEntity<>("Details for Order with Id" + savedOrder.getOrderId() + " are updated!", HttpStatus.OK) ;

	}

	public ResponseEntity<String> deleteById(String OrderId) {
		OrderRepository.deleteByOrderId(OrderId);
		return new ResponseEntity<>("Order with ID " + OrderId + " is Deleted!", HttpStatus.OK) ;
	}
	
	
}

