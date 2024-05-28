package com.product.ecommerce.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(unique = true)

	private String emailId;
	
	private String password;
	
	private Long phoneNumber;

	@OneToMany(cascade = CascadeType.MERGE)
	private Set<Role> roles;

	@OneToMany(cascade = CascadeType.MERGE)
	private Set<Address> addresses;

	public String isValid() {
		List<String> missingAttributes = new ArrayList<String>();
		if(this.getEmailId() == null)
			missingAttributes.add("Email Id");
		if(this.getPassword() == null)
			missingAttributes.add("Password");
		if(this.getName() == null)
			missingAttributes.add("Name");
		
		if(missingAttributes.size() > 0)
			return "Missing required attributes " + String.join(",", missingAttributes);
		
		return "VALID USER";
	}

}
