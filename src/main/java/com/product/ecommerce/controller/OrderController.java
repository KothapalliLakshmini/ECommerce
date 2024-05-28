package com.product.ecommerce.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.ecommerce.model.entity.Order;
import com.product.ecommerce.model.entity.OrderProduct;
import com.product.ecommerce.model.request.OrderRequest;
import com.product.ecommerce.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	OrderService orderService;

	@GetMapping(value = "/all")
	public ResponseEntity<List<Order>> getAllOrders(@RequestParam(name = "page") Integer page,
			@RequestParam(name = "userId") Long userId) {
		return orderService.getAllOrders(page, userId);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
		return orderService.getOrderById(orderId);
	}

	@PostMapping("/addOrder")
	public ResponseEntity<String> postOrder(@RequestBody Order order) {
		return orderService.postOrder(order);
	}

	@PutMapping("/updateOrderStatus/{orderId}")
	public ResponseEntity<String> putOrder(@PathVariable String orderId,@RequestParam(name = "status") String orderStatus ) {
		return orderService.updateOrderStatus(orderId, orderStatus);
	}
	
	@PutMapping("/updatePaymentStatus/{orderId}")
	public ResponseEntity<String> updatePaymentStatus(@PathVariable String orderId,@RequestParam(name = "status") String paymentStatus ) {
		return orderService.updatePaymentStatus(orderId, paymentStatus);
	}

	@DeleteMapping("/{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable String orderId) {
		return orderService.deleteById(orderId);
	}

	@PostMapping("/placeOrderByCartId")
	public ResponseEntity<String> placeOrderByCartId(@RequestBody OrderRequest orderRequest) {
		return orderService.placeOrderByCartId(orderRequest);
	}

	@PostMapping("/placeOrderByProductID")
	public ResponseEntity<String> placeOrderByProductID(@RequestBody OrderRequest orderRequest) {
		return orderService.placeOrderByProductID(orderRequest);
	}
}
