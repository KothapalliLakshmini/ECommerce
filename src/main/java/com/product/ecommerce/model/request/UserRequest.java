package com.product.ecommerce.model.request;

import lombok.Data;

@Data
public class UserRequest {
	private Long id;
	private String name;
	private String emailId;
	private String password;
	private Long phoneNumber;
}
