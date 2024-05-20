package com.product.ecommerce.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "OrderProducts")
public class OrderProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Product product;

	private Integer quantity;

	@ManyToOne
	private User seller;

	@Override
	public String toString() {
		return "OrderProduct [id=" + id + ", product=" + product + ", quantity=" + quantity + ", seller=" + seller
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderProduct other = (OrderProduct) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}	
	
	public String isValid() {
		List<String> missingAttributes = new ArrayList<String>();
		if(this.getId() == null) {
			if(this.getProduct() == null)
				missingAttributes.add("Product");
			if(this.getQuantity() == null )
				missingAttributes.add("Quantity");
			if(this.getSeller() == null)
				missingAttributes.add("Seller");
		}	
		
		if(missingAttributes.size() > 0)
			return "Missing required attributes " + String.join(",", missingAttributes);
		
		return "VALID ORDER PRODUCT";
	}
	
}