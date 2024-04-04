package com.product.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.ecommerce.Repository.UserRepository;
import com.product.ecommerce.model.User;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = userRepository.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	public ResponseEntity<User> getUserById(Integer userId){
		Optional<User> users = userRepository.findById(userId);
		return new ResponseEntity<>(users.get(), HttpStatus.OK);
	}

	public ResponseEntity<String> postUser(User user) {
		User savedUser = userRepository.save(user);
		
		return new ResponseEntity<>("User created with Id " + savedUser.getId(), HttpStatus.OK) ;
	}

	public ResponseEntity<String> putUser(User user) {
		
		User savedUser = userRepository.save(user);	
		return new ResponseEntity<>("Details for user with Id" + savedUser.getId() + " are updated!", HttpStatus.OK) ;

	}

	public ResponseEntity<String> deleteById(Integer userId) {
		userRepository.deleteById(userId);
		return new ResponseEntity<>("User with ID " + userId + " is Deleted!", HttpStatus.OK) ;
	}

}
