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

import com.product.ecommerce.model.Product;
import com.product.ecommerce.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping(value="/all")
	public ResponseEntity<List<Product>> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/{productId}") 
	public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
		return productService.getProductById(productId);
	} 
	
	@GetMapping("/searchProduct/{searchString}") 
	public ResponseEntity<List<Product>> getProduct(@PathVariable String searchString) {
		return productService.searchProducts(searchString);
	} 
	
	@PostMapping()
	public ResponseEntity<String> postProduct(@RequestBody Product product) {
		return productService.postProduct(product);
	}
	
	@PutMapping()
	public ResponseEntity<String> putProduct(@RequestBody Product product) {
		return productService.putProduct(product);
	}
	
	@DeleteMapping("/{productId}") 
	public ResponseEntity<String> deleteProduct(@PathVariable Integer productId) {
		return productService.deleteProductById(productId);
	} 
	
	@GetMapping("/validateExistence")
	public ResponseEntity<List<Product>> validateExistence(@PathVariable Integer productId){
	
		return productService.validateExistenceProduct(productId);
	}


}
