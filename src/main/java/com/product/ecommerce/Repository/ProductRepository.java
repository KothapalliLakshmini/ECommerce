package com.product.ecommerce.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.ecommerce.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByNameContainingOrDescriptionContaining(String nameString, String descriptionString, Pageable pageable);
	Product findByIdAndAvailableQtyGreaterThan(Long productId, Integer availableQty);
	
}
