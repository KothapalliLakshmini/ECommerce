package com.product.ecommerce.model.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Products")
public class Product {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Integer price;
	private Integer availableQty;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Image> images;
	
	@OneToMany
	private Set<User> sellers;
	
}