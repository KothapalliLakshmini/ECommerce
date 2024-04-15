package com.product.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.product.ecommerce.model.entity.Product;
import com.product.ecommerce.model.entity.User;
import com.product.ecommerce.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping(value = "/addProduct", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<String> addProduct(@RequestPart("product") Product product,
			@RequestPart("imageFile") MultipartFile[] images) {
		return productService.postProduct(product, images);
	} 
	
	@PostMapping(value = "/addImagesToProduct/{productId}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<String> addImagesToProduct(@PathVariable Long productId,
			@RequestPart("imageFile") MultipartFile[] images) {
		return productService.addImagesToProduct(productId, images);
	} 
	
	@GetMapping("/searchProduct/{searchString}")
	public ResponseEntity<List<Product>> getProduct(@PathVariable String searchString, @RequestParam(name="page") Integer page) {
		return productService.searchProducts(searchString, page);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<Object> getProduct(@PathVariable Long productId) {
		return productService.getProductById(productId);
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
		return productService.deleteProductById(productId);
	}
	
	
	@PostMapping(value = "/addSellerToProduct/{productId}")
	public ResponseEntity<String> addSellerToProduct(@PathVariable Long productId, User seller) {
		return productService.addSellerToProduct(productId, seller);
	} 

	@GetMapping(value = "/all")
	public ResponseEntity<List<Product>> getAllProducts() {
		return productService.getAllProducts();
	}




	@PutMapping()
	public ResponseEntity<String> putProduct(@RequestBody Product product) {
		return productService.putProduct(product);
	}



	@GetMapping("/validateExistence")
	public ResponseEntity<String> validateExistence(@RequestParam(name = "id") Long productId,
			@RequestParam(name = "rqdQty") Integer requiredQty) {

		return productService.validateExistenceProduct(productId, requiredQty);
	}

}
