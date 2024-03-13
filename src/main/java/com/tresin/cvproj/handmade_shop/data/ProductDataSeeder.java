package com.tresin.cvproj.handmade_shop.data;

import com.tresin.cvproj.handmade_shop.exception.AddressNotFoundException;
import com.tresin.cvproj.handmade_shop.exception.CategoryNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.model.Category;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.repository.ProductRepository;
import com.tresin.cvproj.handmade_shop.service.CategoryService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
@DependsOn("categoryDataSeeder") //Make sure that categories are seeded before products as we are assigning products to categories already while seeding.
public class ProductDataSeeder {

	private static final Logger logger = LoggerFactory.getLogger(ProductDataSeeder.class);
	private final ProductRepository productRepository;
	private final CategoryService categoryService;
	private final boolean seedData;

	@Autowired
	public ProductDataSeeder(ProductRepository productRepository, CategoryService categoryService, @Value("${app.seed.seed-data:false}") boolean seedData) {
		this.productRepository = productRepository;
		this.categoryService = categoryService;
		this.seedData = seedData;
	}

	@PostConstruct
	@Transactional
	public void seedProducts() {
		if (seedData && productRepository.count() == 0) {
			try {
				Category category1 = categoryService.getCategoryById(1L).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
				Category category2 = categoryService.getCategoryById(2L).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
				Category category3 = categoryService.getCategoryById(3L).orElseThrow(() -> new CategoryNotFoundException("Category not found"));

				Product product1 = new Product("Test Product 1", 15.49, List.of(category1));
				Product product2 = new Product("Test Product 2", 19.99, List.of(category2));
				Product product3 = new Product("Test Product 3", 24.99, List.of(category3));

				productRepository.saveAll(Arrays.asList(product1, product2, product3));
			} catch (CategoryNotFoundException e) {
				logger.error("Error while seeding products: " + e.getMessage());
				throw new RuntimeException("Error while seeding products", e);
			}
		}
	}

}
