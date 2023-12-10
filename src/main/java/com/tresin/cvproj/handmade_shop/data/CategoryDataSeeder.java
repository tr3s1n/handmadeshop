package com.tresin.cvproj.handmade_shop.data;

import com.tresin.cvproj.handmade_shop.model.Category;
import com.tresin.cvproj.handmade_shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class CategoryDataSeeder {

	private final CategoryRepository categoryRepository;

	@Autowired
	public CategoryDataSeeder(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@PostConstruct
	public void seedCategories() {
		Category category1 = new Category("Handmade Jewelry");
		Category category2 = new Category("Home Decor");
		Category category3 = new Category("Art and Crafts");

		categoryRepository.saveAll(Arrays.asList(category1, category2, category3));
	}
}
