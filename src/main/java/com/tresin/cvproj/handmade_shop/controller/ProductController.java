package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.ProductApi;
import com.tresin.cvproj.handmade_shop.dto.ProductDTO;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController implements ProductApi {

	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDTO productDTO) {
		Product createdProduct = productService.createProduct(productDTO.toProduct());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
	}

	@Override
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
		Product updatedProduct = productService.createProduct(productDTO.toProduct());
		Product resultProduct = productService.updateProduct(id, updatedProduct);
		return ResponseEntity.ok(resultProduct);
	}

	@Override
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Product>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@Override
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		return ResponseEntity.ok(productService.getProductById(id).orElseThrow());
	}

	@Override
	public ResponseEntity<List<Product>> searchProducts(@PathVariable String keyword, @PathVariable String category, @PathVariable Double minPrice, @PathVariable Double maxPrice) {
		return ResponseEntity.ok(productService.searchProducts(keyword, category, minPrice, maxPrice));
	}

	@Override
	public ResponseEntity<List<Product>> filterProducts(@PathVariable String category, @PathVariable String color) {
		return ResponseEntity.ok(productService.filterProducts(category, color));
	}

	@Override
	public ResponseEntity<List<Product>> sortProducts(@PathVariable String sortBy) {
		return ResponseEntity.ok(productService.sortProducts(sortBy));
	}

	@Override
	public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable Long categoryId) {
		return ResponseEntity.ok(productService.getProductsByCategoryId(categoryId));
	}

	@Override
	public ResponseEntity<Product> getProductByImageId(@PathVariable Long imageId) {
		return ResponseEntity.ok(productService.getProductByImageId(imageId).orElseThrow());
	}

	@Override
	public ResponseEntity<Product> getProductByReviewId(@PathVariable Long reviewId) {
		return ResponseEntity.ok(productService.getProductByReviewId(reviewId).orElseThrow());
	}

	@Override
	public ResponseEntity<List<Product>> getProductsByCartId(@PathVariable Long cartId) {
		return ResponseEntity.ok(productService.getProductsByCartId(cartId));
	}

	@Override
	public ResponseEntity<List<Product>> getProductsByOrderId(@PathVariable Long orderId) {
		return ResponseEntity.ok(productService.getProductsByOrderId(orderId));
	}
}
