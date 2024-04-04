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

import com.product.ecommerce.model.Seller;
import com.product.ecommerce.service.SellerService;

@RestController
@RequestMapping("/sellers")
public class SellerController {
	
	@Autowired
	SellerService sellerService;
	
	@GetMapping(value="/all")
	public ResponseEntity<List<Seller>> getAllSellers() {
		return sellerService.getAllSellers();
	}
	
	@GetMapping("/{sellerId}") 
	public ResponseEntity<Seller> getSeller(@PathVariable Integer sellerId) {
		return sellerService.getSellerById(sellerId);
	} 
	
	@PostMapping()
	public ResponseEntity<String> postSeller(@RequestBody Seller seller) {
		return sellerService.postSeller(seller);
	}
	
	@PutMapping()
	public ResponseEntity<String> putSeller(@RequestBody Seller seller) {
		return sellerService.putSeller(seller);
	}
	
	@DeleteMapping("/{sellerId}") 
	public ResponseEntity<String> deleteSeller(@PathVariable Integer sellerId) {
		return sellerService.deleteById(sellerId);
	} 

}

