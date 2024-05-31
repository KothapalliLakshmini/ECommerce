package com.product.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.ecommerce.model.entity.Role;
import com.product.ecommerce.Repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	public ResponseEntity<String> createRole(String roleName) {
		Role newRole = new Role();
		newRole.setName(roleName);
		Role savedRole = roleRepository.save(newRole);
		
		return new ResponseEntity<String>(String.format("Role %s is created with id %s!", roleName, savedRole.getId()),HttpStatus.OK);
	}

}
