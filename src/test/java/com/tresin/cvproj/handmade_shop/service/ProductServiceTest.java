package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	@Test
	public void testCreateProduct() {
		Product product = new Product("Test Product", 420.69);
		when(productRepository.save(any(Product.class))).thenReturn(product);
		Product createdProduct = productService.createProduct(product);

		assertNotNull(createdProduct);
	}

	@Test
	public void testDeleteProduct() {
		long productIdToDelete = 1L;
		Product productToDelete = new Product("Test Product", 420.69);
		when(productRepository.findById(productIdToDelete)).thenReturn(Optional.of(productToDelete));
		productService.deleteProduct(productIdToDelete);

		verify(productRepository, times(1)).deleteById(productIdToDelete);
	}

	@Test
	public void testUpdateProduct() {
		long productIdToUpdate = 1L;
		Product existingProduct = new Product();
		Product updatedProductData = new Product();
		when(productRepository.findById(productIdToUpdate)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));
		Product updatedProduct = productService.updateProduct(productIdToUpdate, updatedProductData);

		assertNotNull(updatedProduct);
		assertEquals(updatedProductData.getName(), updatedProduct.getName());
		assertEquals(updatedProductData.getPrice(), updatedProduct.getPrice());
	}

	@Test
	public void testGetAllProducts() {
		List<Product> productList = Arrays.asList(
				new Product("Test Product 1", 19.99),
				new Product("Test Product 2", 6.99)
		);
		when(productRepository.findAll()).thenReturn(productList);
		List<Product> allProducts = productService.getAllProducts();

		assertNotNull(allProducts);
		assertEquals(productList.size(), allProducts.size());
	}

	@Test
	public void testGetProductById() {
		long productIdToFind = 1L;
		Product foundProduct = new Product("Test Product", 1.99);
		when(productRepository.findById(productIdToFind)).thenReturn(Optional.of(foundProduct));
		Optional<Product> productById = productService.getProductById(productIdToFind);

		assertTrue(productById.isPresent());
		assertEquals(foundProduct, productById.get());
	}
}
