package com.product.ecommerce.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.ecommerce.model.entity.Cart;
import com.product.ecommerce.model.entity.OrderProduct;
import com.product.ecommerce.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	CartRepository cartRepository;
	
	public Optional<Cart> findCartById(Long cartId){
		return cartRepository.findById(cartId);
	}

	public ResponseEntity<String> addProductToCart(OrderProduct orderProduct, Long cartId) {
		String orderProductValidity = orderProduct.isValid();
		if (orderProductValidity.equals("VALID ORDER PRODUCT")) {
			if (cartId != null) {
				Optional<Cart> savedOptionalCart = findCartById(cartId);
				if (savedOptionalCart.isEmpty()) {
					return createCart(orderProduct);
				} else {
					Cart savedCart = savedOptionalCart.get();
					savedCart.getOrderProducts().add(orderProduct);
					cartRepository.save(savedCart);
					return new ResponseEntity<>(String.format("Added order products to cart with id %s", cartId),
							HttpStatus.OK);
				}
			} else {
				return createCart(orderProduct);
			}
		} else {
			return new ResponseEntity<>(String.format("Provided order product is not valid. %s", orderProductValidity),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<String> createCart(OrderProduct orderProduct) {
		Cart cart = new Cart();
		Set<OrderProduct> newOrderProductsSet = new HashSet<>();
		newOrderProductsSet.add(orderProduct);
		cart.setOrderProducts(newOrderProductsSet);
		cartRepository.save(cart);
		return new ResponseEntity<>("Created cart and added products to cart!", HttpStatus.OK);
	}

	public ResponseEntity<Object> getAllProductsInCart(Long cartId) {

		Optional<Cart> savedOptionalCart = findCartById(cartId);
		if (!savedOptionalCart.isEmpty()) {
			return new ResponseEntity<Object>(savedOptionalCart.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(String.format("No cart is found with id %s", cartId), HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<String> deleteProductFromCart(Long cartId, OrderProduct orderProduct) {
		Optional<Cart> savedOptionalCart = findCartById(cartId);
		if (!savedOptionalCart.isEmpty()) {
			Cart savedCart = savedOptionalCart.get();
			Set<OrderProduct> savedOrderProducts = savedCart.getOrderProducts();
			Iterator<OrderProduct> iterator = savedOrderProducts.iterator();
			while (iterator.hasNext()) {
				OrderProduct savedOrderProduct = iterator.next();
				if (savedOrderProduct.equals(orderProduct))
					iterator.remove();
			}
			savedCart.setOrderProducts(savedOrderProducts);
			cartRepository.save(savedCart);
			return new ResponseEntity<>(String.format("Mentioned order product is removed from cart %s", cartId),
					HttpStatus.OK);

		} else {
			return new ResponseEntity<>(String.format("No cart is found with id %s", cartId), HttpStatus.NOT_FOUND);
		}
	}
}
