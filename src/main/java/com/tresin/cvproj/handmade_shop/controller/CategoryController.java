package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.CategoryApi;
import com.tresin.cvproj.handmade_shop.dto.CategoryDTO;
import com.tresin.cvproj.handmade_shop.model.Category;
import com.tresin.cvproj.handmade_shop.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController implements CategoryApi {

	private final CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
		Category newCategory = new Category();
		newCategory.setName(categoryDTO.getName());
		newCategory.setProducts(categoryDTO.getProducts());
		Category createdCategory = categoryService.createCategory(newCategory);

		return ResponseEntity.ok(createdCategory);
	}

	@Override
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
		Category updatedCategory = new Category();
		updatedCategory.setName(categoryDTO.getName());
		updatedCategory.setProducts(categoryDTO.getProducts());
		Category resultCategory = categoryService.updateCategory(id, updatedCategory);

		if (resultCategory != null) {
			return ResponseEntity.ok(resultCategory);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = categoryService.getAllCategories();

		return ResponseEntity.ok(categories);
	}

	@Override
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
		return categoryService.getCategoryById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

}
