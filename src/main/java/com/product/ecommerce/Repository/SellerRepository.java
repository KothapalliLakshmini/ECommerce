package com.product.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.ecommerce.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer>{

}

