package com.product.ecommerce.model.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Payments")
public class Payment {
	
	public Payment() {
		setTransactionId();
	}
	
	public Payment(String paymentMode) {
		setTransactionId();
		this.paymentMode = paymentMode;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String transactionId;
	
	@OneToOne
	private PaymentStatus paymentStatus;
	
	private String paymentMode;	
	
	private Integer amount;
	
	private void setTransactionId() {
		this.transactionId = UUID.randomUUID().toString();
	}
}
