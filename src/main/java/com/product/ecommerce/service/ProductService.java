package com.product.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.ecommerce.Repository.ProductRepository;
import com.product.ecommerce.model.Product;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> Products = productRepository.findAll();
		return new ResponseEntity<>(Products, HttpStatus.OK);
	}
	
	
	public ResponseEntity<List<Product>> searchProducts(String searchString){
		List<Product> Products = productRepository.findByNameContainingOrDescriptionContaining(searchString, searchString);
		return new ResponseEntity<>(Products, HttpStatus.OK);
	}
	
	public ResponseEntity<Product> getProductById(Integer productId){ 
		Optional<Product> Products = productRepository.findById(productId);
		return new ResponseEntity<>(Products.get(), HttpStatus.OK);
	}

	public ResponseEntity<String> postProduct(Product product) {
		Product savedProduct = productRepository.save(product);
		
		return new ResponseEntity<>("Product created with Id " + savedProduct.getId(), HttpStatus.OK) ;
	}

	public ResponseEntity<String> putProduct(Product product) {
		
		Product savedProduct = productRepository.save(product);	
		return new ResponseEntity<>("Details for product with Id" +savedProduct.getId() + " are updated!", HttpStatus.OK) ;

	}

	public ResponseEntity<String> deleteProductById(Integer productId) {
		productRepository.deleteById(productId);
		return new ResponseEntity<>("Product with ID " + productId + " is Deleted!", HttpStatus.OK) ;
	}
	
	public ResponseEntity<List<Product>> validateExistenceProduct(Integer productId){
		List<Product> Exists = productRepository.findByAvailableQty(productId);
			if(Exists != null) {
		return new ResponseEntity<>(HttpStatus.OK);
	}else {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
			
}
	
}
