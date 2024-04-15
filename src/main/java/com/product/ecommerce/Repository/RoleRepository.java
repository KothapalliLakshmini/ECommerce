package com.product.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.ecommerce.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>  {

}
