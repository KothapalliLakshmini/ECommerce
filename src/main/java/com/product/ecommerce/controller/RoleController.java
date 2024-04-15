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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.ecommerce.model.entity.Address;
import com.product.ecommerce.model.entity.User;
import com.product.ecommerce.service.RoleService;
import com.product.ecommerce.service.UserService;


@RestController
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@PostMapping("/createRole")
	public ResponseEntity<String> postUser(@RequestParam String roleName) {
		return roleService.createRole(roleName);
	}
	
}

