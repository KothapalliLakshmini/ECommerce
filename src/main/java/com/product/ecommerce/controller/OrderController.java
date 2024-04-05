package com.product.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.ecommerce.model.Order;
import com.product.ecommerce.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping(value = "/all")
	public ResponseEntity<List<Order>> getAllOrders() {
		return orderService.getAllOrders();

	}

	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
		return orderService.getOrderById(orderId);
	}

	@PostMapping("/addOrder")
	public ResponseEntity<String> postOrder(@RequestBody Order order) {
		return orderService.postOrder(order);
	}

	@PutMapping("/updateOrder")
	public ResponseEntity<String> putOrder(@RequestBody Order order) {
		return orderService.putOrder(order);
	}

	@DeleteMapping("/{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable String orderId) {
		return orderService.deleteById(orderId);
	}
	
}
