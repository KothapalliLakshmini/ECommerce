package com.product.ecommerce.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Orders {
	
	@Id
	private String orderId;
	
	@OneToOne
	private Products products;
	
	@OneToOne
	private Address address;
	
	@OneToOne
	private Users user;
	
	private Integer productQty;
	
	@OneToOne
	private PaymentStatus paymentStatus;
	
	@OneToOne
	private OrderStatus orderStatus;
	
	
	
}