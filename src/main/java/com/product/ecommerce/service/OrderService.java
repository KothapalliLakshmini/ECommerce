package com.product.ecommerce.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.ecommerce.model.entity.Cart;
import com.product.ecommerce.model.entity.Order;
import com.product.ecommerce.model.entity.OrderProduct;
import com.product.ecommerce.model.entity.OrderStatus;
import com.product.ecommerce.model.entity.Payment;
import com.product.ecommerce.model.entity.PaymentStatus;
import com.product.ecommerce.model.entity.User;
import com.product.ecommerce.model.request.OrderRequest;
import com.product.ecommerce.repository.OrderRepository;
import com.product.ecommerce.repository.UserRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CartService cartService;

	public ResponseEntity<List<Order>> getAllOrders(int page, Long userId) {
		Pageable pageable = PageRequest.of(page - 1, 5);
		List<Order> orders = orderRepository.findByUserId(userId, pageable);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	public ResponseEntity<Order> getOrderById(String OrderId) {
		Optional<Order> orders = orderRepository.findById(OrderId);
		return new ResponseEntity<>(orders.get(), HttpStatus.OK);
	}

	public ResponseEntity<String> postOrder(Order Order) {
		Order savedOrder = orderRepository.save(Order);

		return new ResponseEntity<>("Order created with Id " + savedOrder.getOrderId(), HttpStatus.OK);
	}

	public ResponseEntity<String> putOrder(Order Order) {

		Order savedOrder = orderRepository.save(Order);
		return new ResponseEntity<>("Details for Order with Id" + savedOrder.getOrderId() + " are updated!",
				HttpStatus.OK);

	}

	public ResponseEntity<String> deleteById(String OrderId) {
		orderRepository.deleteByOrderId(OrderId);
		return new ResponseEntity<>("Order with ID " + OrderId + " is Deleted!", HttpStatus.OK);
	}

	public ResponseEntity<String> placeOrderByCartId(OrderRequest orderRequest) {

		if (orderRequest.isValid().equals("VALID ORDER REQUEST")) {

			Optional<Cart> optionalCart = cartService.findCartById(orderRequest.getCartId());

			if (optionalCart.isPresent()) {
				Set<OrderProduct> savedOrderProducts = optionalCart.get().getOrderProducts();
				Payment newPayment = new Payment(orderRequest.getPaymentMode());
				Order newOrder = new Order();
				newOrder.setOrderProducts(savedOrderProducts);
				newOrder.setPayment(newPayment);
				newOrder.setAddress(orderRequest.getShippingAddress());
				Optional<OrderStatus> optionalOrderStatus = orderRepository.findByOrderStatus("ORDER_PLACED");
				if (optionalOrderStatus.isPresent()) {
					newOrder.setOrderStatus(optionalOrderStatus.get());
				}
				orderRepository.save(newOrder);
				return new ResponseEntity<String>(
						String.format("Order Placed for all order products in mentioned cart with id %s",
								orderRequest.getCartId()),
						HttpStatus.OK);

			} else {
				return new ResponseEntity<String>(
						String.format("No cart is found with id %s", orderRequest.getCartId()), HttpStatus.NOT_FOUND);

			}
		} else {
			return new ResponseEntity<String>("Given Order request is not valid.", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> placeOrderByProductID(OrderRequest orderRequest) {
		if (orderRequest.isValid().equals("VALID ORDER REQUEST")) {
			if (orderRequest.getOrderProduct().isValid().equals("VALID ORDER PRODUCT")) {
				Set<OrderProduct> savedOrderProducts = new HashSet<>();
				Payment newPayment = new Payment(orderRequest.getPaymentMode());
				Order newOrder = new Order();
				newOrder.setOrderProducts(savedOrderProducts);
				newOrder.setPayment(newPayment);
				newOrder.setAddress(orderRequest.getShippingAddress());
				orderRepository.save(newOrder);
			} else {
				return new ResponseEntity<String>(
						String.format("Order product is not valid. %s", orderRequest.getOrderProduct().isValid()),
						HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<String>(String.format("Order request is not valid. %s", orderRequest.isValid()),
					HttpStatus.NOT_FOUND);
		}

		return null;
	}

	public ResponseEntity<String> updateOrderStatus(String orderId, String orderStatus) {

		Optional<OrderStatus> optionalOrderStatus = orderRepository.findByOrderStatus(orderStatus);

		if (optionalOrderStatus.isPresent()) {
			Optional<Order> optionalDbOrder = orderRepository.findByOrderId(orderId);
			if (optionalDbOrder.isPresent()) {
				Order dbOrder = optionalDbOrder.get();
				dbOrder.setOrderStatus(optionalOrderStatus.get());
				orderRepository.save(dbOrder);
				return new ResponseEntity<String>(String.format("Order status is updated for order id %s.", orderId),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(String.format("Order Id is not valid. %s", orderId),
						HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<String>(String.format("Order status is not valid. %s", orderStatus),
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> updatePaymentStatus(String orderId, String paymentStatus) {

		Optional<PaymentStatus> optionalPaymentStatus = orderRepository.findByPaymentStatus(paymentStatus);

		if (optionalPaymentStatus.isPresent()) {
			Optional<Order> optionalDbOrder = orderRepository.findByOrderId(orderId);
			if (optionalDbOrder.isPresent()) {
				Order dbOrder = optionalDbOrder.get();
				dbOrder.getPayment().setPaymentStatus(optionalPaymentStatus.get());
				orderRepository.save(dbOrder);
				return new ResponseEntity<String>(String.format("Payment status is updated for order id %s.", orderId),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(String.format("Order Id is not valid. %s", orderId),
						HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<String>(String.format("Payment status is not valid. %s", paymentStatus),
					HttpStatus.BAD_REQUEST);
		}
	}

}
