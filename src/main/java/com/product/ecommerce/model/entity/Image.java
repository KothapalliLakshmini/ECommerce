package com.product.ecommerce.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Image")
public class Image {
	
	public Image(String name, String type, byte[] picByte) {
		super();
		this.name = name;
		this.type = type;
		this.picByte = picByte;
	}
	public Image() {
		super();
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private String name;
    
    private String type;
    
    @Column(length = 50000000)
    private byte[] picByte;

	
}
