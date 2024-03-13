package com.tresin.cvproj.handmade_shop.data;

import com.tresin.cvproj.handmade_shop.model.Category;
import com.tresin.cvproj.handmade_shop.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;

@Component
@DependsOn("roleDataSeeder")
public class CategoryDataSeeder {

	private final CategoryRepository categoryRepository;
	private final boolean seedData;

	@Autowired
	public CategoryDataSeeder(CategoryRepository categoryRepository, @Value("${app.seed.seed-data:false}") boolean seedData) {
		this.categoryRepository = categoryRepository;
		this.seedData = seedData;
	}

	@PostConstruct
	@Transactional
	public void seedCategories() {
		if (seedData && categoryRepository.count() == 0) {
			Category category1 = new Category("Handmade Jewelry");
			Category category2 = new Category("Home Decor");
			Category category3 = new Category("Art and Crafts");

			categoryRepository.saveAll(Arrays.asList(category1, category2, category3));
		}
	}
}
