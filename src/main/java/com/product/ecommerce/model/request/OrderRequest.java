package com.product.ecommerce.model.request;

import java.util.ArrayList;
import java.util.List;

import com.product.ecommerce.model.entity.Address;
import com.product.ecommerce.model.entity.OrderProduct;

import lombok.Data;

@Data
public class OrderRequest {

	private Address shippingAddress;

	private String paymentMode;

	private Long cartId;
	
	private OrderProduct orderProduct;

	public String isValid() {
		List<String> missingAttributes = new ArrayList<String>();
		if (this.getShippingAddress() == null || this.getShippingAddress().getId() == null) {
			missingAttributes.add("Shipping Address");
		}
		if (this.getCartId() == null && this.getOrderProduct() == null) {
			missingAttributes.add("Cart Id or Order Product");
		}
		
		if (missingAttributes.size() > 0)
			return "Missing required attributes " + String.join(",", missingAttributes);

		return "VALID ORDER REQUEST";
	}

}
