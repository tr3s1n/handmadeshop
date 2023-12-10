package com.tresin.cvproj.handmade_shop.data;

import com.tresin.cvproj.handmade_shop.model.Category;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.repository.ProductRepository;
import com.tresin.cvproj.handmade_shop.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@DependsOn("categoryDataSeeder") //Make sure that categories are seeded before products as we are assigning products to categories already while seeding.
public class ProductDataSeeder {

	private static final Logger logger = LoggerFactory.getLogger(ProductDataSeeder.class);
	private final ProductRepository productRepository;
	private final CategoryService categoryService;

	@Autowired
	public ProductDataSeeder(ProductRepository productRepository, CategoryService categoryService) {
		this.productRepository = productRepository;
		this.categoryService = categoryService;
	}

	@PostConstruct
	public void seedProducts() {
		Optional<Category> optionalCategory1 = categoryService.getCategoryById(1L);
		Optional<Category> optionalCategory2 = categoryService.getCategoryById(2L);
		Optional<Category> optionalCategory3 = categoryService.getCategoryById(3L);

		if (optionalCategory1.isPresent() && optionalCategory2.isPresent() && optionalCategory3.isPresent()) {
			Category category1 = optionalCategory1.get();
			Category category2 = optionalCategory2.get();
			Category category3 = optionalCategory3.get();

			Product product1 = new Product("Test Product 1", 15.99, List.of(category1));
			Product product2 = new Product("Test Product 2", 19.99, List.of(category2));
			Product product3 = new Product("Test Product 3", 24.99, List.of(category3));

			productRepository.saveAll(Arrays.asList(product1, product2, product3));
		} else {
			logger.warn("One or more of categories was not present, thus no Product objects were seeded");
		}
	}
}
