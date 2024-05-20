package com.product.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.ecommerce.model.entity.OrderProduct;
import com.product.ecommerce.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;

	@PostMapping(value = { "/addProductToCart", "/addProductToCart/{cartId}" })
	public ResponseEntity<String> addProductToCart(@RequestBody OrderProduct orderProduct,
			@PathVariable(required = false) Long cartId) {
		return cartService.addProductToCart(orderProduct, cartId);
	}
	
	@GetMapping("/getAllProducts/{cartId}")
	public ResponseEntity<Object> getAllProductsInCart(@PathVariable Long cartId){
		return cartService.getAllProductsInCart(cartId);
	}
	
	@DeleteMapping("/deleteProductFromCart/{cartId}")
	public ResponseEntity<String> deleteProductFromCart(@PathVariable Long cartId, @RequestBody OrderProduct orderProduct){
		return cartService.deleteProductFromCart(cartId, orderProduct);
	}
}
