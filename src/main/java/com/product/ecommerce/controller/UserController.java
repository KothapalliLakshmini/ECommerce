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

import com.product.ecommerce.model.entity.Address;
import com.product.ecommerce.model.entity.User;
import com.product.ecommerce.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(value="/all")
	public ResponseEntity<List<User>> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/{userId}") 
	public ResponseEntity<User> getUser(@PathVariable Integer userId) {
		return userService.getUserById(userId);
	} 
	
	@PostMapping("/signup")
	public ResponseEntity<String> postUser(@RequestBody User user) {
		return userService.postUser(user);
	}
	
	@PostMapping("/saveAddress/{userId}")
	public ResponseEntity<String> saveAddress(@PathVariable Long userId, @RequestBody Address address) {
		return userService.saveAddress(userId, address);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/{userId}") 
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
		return userService.deleteById(userId);
	} 

}
