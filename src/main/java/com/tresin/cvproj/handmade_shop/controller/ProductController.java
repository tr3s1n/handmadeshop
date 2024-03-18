package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.ProductApi;
import com.tresin.cvproj.handmade_shop.dto.ProductDTO;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController implements ProductApi {

	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@Override
	@PostMapping
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
	@PutMapping("/{id}")
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
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);

		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();

		return ResponseEntity.ok(products);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		return productService.getProductById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

}
