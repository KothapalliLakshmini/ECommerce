package com.product.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.ecommerce.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findById(Long id);

}
