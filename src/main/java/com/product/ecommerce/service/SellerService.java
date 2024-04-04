package com.product.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.ecommerce.Repository.SellerRepository;
import com.product.ecommerce.model.Seller;

@Service
public class SellerService {
	
	@Autowired
	SellerRepository sellerRepository;
	
	public ResponseEntity<List<Seller>> getAllSellers(){
		List<Seller> sellers = sellerRepository.findAll();
		return new ResponseEntity<>(sellers, HttpStatus.OK);
	}
	
	public ResponseEntity<Seller> getSellerById(Integer sellerId){
		Optional<Seller> sellers = sellerRepository.findById(sellerId);
		return new ResponseEntity<>(sellers.get(), HttpStatus.OK);
	}

	public ResponseEntity<String> postSeller(Seller seller) {
		Seller savedSeller = sellerRepository.save(seller);
		
		return new ResponseEntity<>("Seller created with Id " + savedSeller.getId(), HttpStatus.OK) ;
	}

	public ResponseEntity<String> putSeller(Seller seller) {
		
		Seller savedSeller = sellerRepository.save(seller);	
		return new ResponseEntity<>("Details for seller with Id" + savedSeller.getId() + " are updated!", HttpStatus.OK) ;

	}

	public ResponseEntity<String> deleteById(Integer sellerId) {
		sellerRepository.deleteById(sellerId);
		return new ResponseEntity<>("Seller with ID " + sellerId + " is Deleted!", HttpStatus.OK) ;
	}

}