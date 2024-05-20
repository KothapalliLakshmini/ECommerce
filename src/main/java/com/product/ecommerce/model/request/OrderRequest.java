package com.product.ecommerce.model.request;

import com.product.ecommerce.model.entity.Address;

import lombok.Data;

@Data
public class OrderRequest {
	
	private Address shippingAddress;
	
	private String paymentMode;
	
	private Long cartId;

}
