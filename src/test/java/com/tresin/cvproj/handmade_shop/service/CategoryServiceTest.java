package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.model.Category;
import com.tresin.cvproj.handmade_shop.repository.CategoryRepository;
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
public class CategoryServiceTest {

	@InjectMocks
	private CategoryService categoryService;

	@Mock
	private CategoryRepository categoryRepository;

	@Test
	public void testCreateCategory() {
		Category category = new Category("Test Category");
		when(categoryRepository.save(any(Category.class))).thenReturn(category);
		Category createdCategory = categoryService.createCategory(category);

		assertNotNull(createdCategory);
	}

	@Test
	public void testDeleteCategory() {
		long categoryIdToDelete = 1L;
		Category categoryToDelete = new Category("Test Category");
		when(categoryRepository.findById(categoryIdToDelete)).thenReturn(Optional.of(categoryToDelete));
		categoryService.deleteCategory(categoryIdToDelete);

		verify(categoryRepository, times(1)).deleteById(categoryIdToDelete);
	}

	@Test
	public void testUpdateCategory() {
		long categoryIdToUpdate = 1L;
		Category existingCategory = new Category("Test Category");
		Category updatedCategoryData = new Category("Updated Test Category");
		when(categoryRepository.findById(categoryIdToUpdate)).thenReturn(Optional.of(existingCategory));
		when(categoryRepository.save(any(Category.class))).thenAnswer(invocation -> invocation.getArgument(0));
		Category updatedCategory = categoryService.updateCategory(categoryIdToUpdate, updatedCategoryData);

		assertNotNull(updatedCategory);
		assertEquals(updatedCategoryData.getName(), updatedCategory.getName());
		assertEquals(updatedCategoryData.getProducts(), updatedCategory.getProducts());
	}

	@Test
	public void testGetAllCategories() {
		List<Category> categoryList = Arrays.asList(
				new Category("Test Category 1"),
				new Category("Test Category 2")
		);
		when(categoryRepository.findAll()).thenReturn(categoryList);
		List<Category> allCategories = categoryService.getAllCategories();

		assertNotNull(allCategories);
		assertEquals(categoryList.size(), allCategories.size());
	}

	@Test
	public void testGetCategoryById() {
		long categoryIdToFind = 1L;
		Category foundCategory = new Category("Test Category");
		when(categoryRepository.findById(categoryIdToFind)).thenReturn(Optional.of(foundCategory));
		Optional<Category> categoryById = categoryService.getCategoryById(categoryIdToFind);

		assertTrue(categoryById.isPresent());
		assertEquals(foundCategory, categoryById.get());
	}
}
