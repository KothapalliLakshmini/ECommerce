package com.product.ecommerce.model.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Orders")
public class Order {

	@Id
	private String orderId;

	@OneToMany
	private List<OrderProduct> orderProducts;

	@OneToOne
	private Address address;

	@OneToOne
	private User user;

	@OneToOne
	private Payment payment;

	@OneToOne
	private OrderStatus orderStatus;

}