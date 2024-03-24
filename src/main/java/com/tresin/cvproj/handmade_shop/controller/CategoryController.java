package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.CategoryApi;
import com.tresin.cvproj.handmade_shop.dto.CategoryDTO;
import com.tresin.cvproj.handmade_shop.model.Category;
import com.tresin.cvproj.handmade_shop.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		Category createdCategory = categoryService.createCategory(categoryDTO.toCategory());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
	}

	@Override
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
		Category updatedCategory = categoryService.createCategory(categoryDTO.toCategory());
		Category resultCategory = categoryService.updateCategory(id, updatedCategory);
		return ResponseEntity.ok(resultCategory);
	}

	@Override
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Category>> getAllCategories() {
		return ResponseEntity.ok(categoryService.getAllCategories());
	}

	@Override
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
		return ResponseEntity.ok(categoryService.getCategoryById(id).orElseThrow());
	}

	@Override
	public ResponseEntity<List<Category>> getCategoriesByProductId(@PathVariable Long productId) {
		return ResponseEntity.ok(categoryService.getCategoriesByProductId(productId));
	}

}
