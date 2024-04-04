package com.product.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.ecommerce.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
