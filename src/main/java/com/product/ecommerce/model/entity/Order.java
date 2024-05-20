package com.product.ecommerce.model.entity;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Orders")
public class Order {
	
	public Order() {
		this.orderId = UUID.randomUUID().toString();
	}

	@Id
	private String orderId;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<OrderProduct> orderProducts;

	@ManyToOne
	private Address address;

	@ManyToOne
	private User user;

	@OneToOne
	private Payment payment;

	@ManyToOne
	private OrderStatus orderStatus;

}