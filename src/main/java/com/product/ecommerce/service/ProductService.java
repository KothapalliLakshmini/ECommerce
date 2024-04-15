package com.product.ecommerce.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.product.ecommerce.model.entity.Image;
import com.product.ecommerce.model.entity.Product;
import com.product.ecommerce.model.entity.User;
import com.product.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	public ResponseEntity<List<Product>> searchProducts(String searchString, Integer page) {

		Pageable pageRequest = PageRequest.of(page, 1);
		List<Product> products = productRepository.findByNameContainingOrDescriptionContaining(searchString,
				searchString, pageRequest);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	public ResponseEntity<Object> getProductById(Long productId) {
		Optional<Product> products = productRepository.findById(productId);
		if (products.isPresent()) {
			return new ResponseEntity<>(products.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(String.format("Product with ID %s is not present.",productId),
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> postProduct(Product product, MultipartFile[] images) {
		try {

			Set<Image> productImages = processImageFiles(images);
			product.setImages(productImages);

			Product savedProduct = productRepository.save(product);

			return new ResponseEntity<>(String.format("Product created with Id %s!", savedProduct.getId()),
					HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<String> addImagesToProduct(Long productId, MultipartFile[] images) {
		try {

			Set<Image> productImages = processImageFiles(images);

			Optional<Product> optionalProduct = productRepository.findById(productId);

			if (optionalProduct.isPresent()) {
				Product savedProduct = optionalProduct.get();
				savedProduct.setImages(productImages);
				productRepository.save(savedProduct);
				return new ResponseEntity<>(String.format("Images added to the product %s!", productId), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(String.format("Product with ID %s is not present.", productId),
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public Set<Image> processImageFiles(MultipartFile[] multipartFiles) throws IOException {
		Set<Image> imageModels = new HashSet<>();

		for (MultipartFile file : multipartFiles) {
			Image imageModel = new Image(file.getOriginalFilename(), getFileType(file.getOriginalFilename()),
					file.getBytes());
			imageModels.add(imageModel);
		}

		return imageModels;
	}

	public String getFileType(String filename) {
		int lastDotIndex = filename.lastIndexOf(".");
		if (lastDotIndex != -1) {
			return filename.substring(lastDotIndex + 1);
		}
		return "";
	}

	public ResponseEntity<String> putProduct(Product product) {
		Product savedProduct = productRepository.save(product);
		return new ResponseEntity<>(String.format("Details updated for product with Id %s", product.getId()),
				HttpStatus.OK);
	}

	public ResponseEntity<String> deleteProductById(Long productId) {
		productRepository.deleteById(productId);
		return new ResponseEntity<>(String.format("Product with ID %s is Deleted!", productId), HttpStatus.OK);
	}

	public ResponseEntity<String> validateExistenceProduct(Long productId, Integer requiredQty) {
		Product product = productRepository.findByIdAndAvailableQtyGreaterThan(productId, requiredQty);
		if (product != null) {
			return new ResponseEntity<String>(
					String.format("Product with ID %s is available with %s quantity.", productId, requiredQty),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	public ResponseEntity<String> addSellerToProduct(Long productId, User seller) {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if (optionalProduct.isPresent()) {
			Product savedProduct = optionalProduct.get();
			Set<User> sellers = savedProduct.getSellers();
			sellers.add(seller);
			productRepository.save(savedProduct);
			return new ResponseEntity<>(String.format("Seller is added to the product %s!", productId), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(String.format("Product with ID %s is not present.", productId),
					HttpStatus.BAD_REQUEST);
		}
	}

}
