package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.ProductApi;
import com.tresin.cvproj.handmade_shop.dto.ProductDTO;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
		Product newProduct = new Product();
		newProduct.setName(productDTO.getName());
		newProduct.setPrice(productDTO.getPrice());
		newProduct.setImages(productDTO.getImages());
		newProduct.setCategories(productDTO.getCategories());
		Product createdProduct = productService.createProduct(newProduct);

		return ResponseEntity.ok(createdProduct);
	}

	@Override
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
		Product updatedProduct = new Product();
		updatedProduct.setName(productDTO.getName());
		updatedProduct.setPrice(productDTO.getPrice());
		updatedProduct.setCategories(productDTO.getCategories());
		updatedProduct.setImages(productDTO.getImages());
		Product resultProduct = productService.updateProduct(id, updatedProduct);

		if (resultProduct != null) {
			return ResponseEntity.ok(resultProduct);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();

		return ResponseEntity.ok(products);
	}

	@Override
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		return productService.getProductById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<List<Product>> searchProducts(String keyword, String category, Double minPrice, Double maxPrice) {
		return null;
	}

	@Override
	public ResponseEntity<List<Product>> filterProducts(String category, String color) {
		return null;
	}

	@Override
	public ResponseEntity<List<Product>> sortProducts(String sortBy) {
		return null;
	}

	@Override
	public ResponseEntity<List<Product>> getProductsByCategoryId(Long categoryId) {
		return null;
	}

	@Override
	public ResponseEntity<Product> getProductByImageId(Long imageId) {
		return null;
	}

	@Override
	public ResponseEntity<Product> getProductByReviewId(Long reviewId) {
		return null;
	}

	@Override
	public ResponseEntity<List<Product>> getProductsByCartId(Long cartId) {
		return null;
	}

	@Override
	public ResponseEntity<List<Product>> getProductsByOrderId(Long orderId) {
		return null;
	}
}
