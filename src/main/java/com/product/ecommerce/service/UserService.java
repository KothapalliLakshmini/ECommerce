package com.product.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.ecommerce.model.entity.Address;
import com.product.ecommerce.model.entity.User;
import com.product.ecommerce.Repository.UserRepository;

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

	public ResponseEntity<String> updateUser(User modifiedUser) {
		User savedUser = userRepository.findById(modifiedUser.getId());
		if(!modifiedUser.isValid().equals("VALID USER"))
			return new ResponseEntity<>(modifiedUser.isValid(), HttpStatus.BAD_REQUEST) ;
		modifiedUser.setAddresses(savedUser.getAddresses());
		User updatedUser = userRepository.save(modifiedUser);	
		return new ResponseEntity<>("Details for user with Id" + savedUser.getId() + " are updated!", HttpStatus.OK) ;

	}

	public ResponseEntity<String> deleteById(Integer userId) {
		userRepository.deleteById(userId);
		return new ResponseEntity<>("User with ID " + userId + " is Deleted!", HttpStatus.OK) ;
	}

	public ResponseEntity<String> saveAddress(Long userId, Address address) {
		
		User savedUser = userRepository.findById(userId);
		Set<Address> userAddresses = savedUser.getAddresses();
		userAddresses.add(address);
		savedUser.setAddresses(userAddresses);
		userRepository.save(savedUser);
		return new ResponseEntity<>("Added address for user with ID " + userId, HttpStatus.OK) ;
	}

}
