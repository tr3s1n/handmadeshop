package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.model.Cart;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.model.User;
import com.tresin.cvproj.handmade_shop.repository.CartRepository;
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
public class CartServiceTest {

	@InjectMocks
	private CartService cartService;

	@Mock
	private CartRepository cartRepository;

	@Test
	public void testCreateCart() {
		User testUser = new User("User 1", "Password123", "User@gmail.com", new Address("Testovacieho 11", "Košice", "040 01"));
		List<Product> testProducts = Arrays.asList(
				new Product("Product 1", 10.99),
				new Product("Product 2", 20.49)
		);

		Cart cart = new Cart(testUser, testProducts);
		when(cartRepository.save(any(Cart.class))).thenReturn(cart);
		Cart createdCart = cartService.createCart(cart);

		assertNotNull(createdCart);
	}

	@Test
	public void testDeleteCart() {
		long cartIdToDelete = 1L;

		User testUser = new User("User 1", "Password123", "User@gmail.com", new Address("Testovacieho 11", "Košice", "040 01"));
		List<Product> testProducts = Arrays.asList(
				new Product("Product 1", 10.99),
				new Product("Product 2", 20.49)
		);

		Cart cartToDelete = new Cart(testUser, testProducts);
		when(cartRepository.findById(cartIdToDelete)).thenReturn(Optional.of(cartToDelete));
		cartService.deleteCart(cartIdToDelete);

		verify(cartRepository, times(1)).deleteById(cartIdToDelete);
	}

	@Test
	public void testUpdateCart() {
		long cartIdToUpdate = 1L;

		User testUser = new User("User 1", "Password123", "User@gmail.com", new Address("Testovacieho 11", "Košice", "040 01"));
		List<Product> testProducts = Arrays.asList(
				new Product("Product 1", 10.99),
				new Product("Product 2", 20.49)
		);

		List<Product> testUpdatedProducts = Arrays.asList(
				new Product("Product 1", 10.99),
				new Product("Product 2", 20.49),
				new Product("Product 3", 12.99)
		);

		Cart existingCart = new Cart(testUser, testProducts);
		Cart updatedCartData = new Cart(testUser, testUpdatedProducts);
		when(cartRepository.findById(cartIdToUpdate)).thenReturn(Optional.of(existingCart));
		when(cartRepository.save(any(Cart.class))).thenAnswer(invocation -> invocation.getArgument(0));
		Cart updatedCart = cartService.updateCart(cartIdToUpdate, updatedCartData);

		assertNotNull(updatedCart);
		assertEquals(updatedCartData.getUser(), updatedCart.getUser());
		assertEquals(updatedCartData.getProducts(), updatedCart.getProducts());
	}

	@Test
	public void testGetAllCarts() {
		User testUser = new User("User 1", "Password123", "User@gmail.com", new Address("Testovacieho 11", "Košice", "040 01"));
		List<Product> testProducts = Arrays.asList(
				new Product("Product 1", 10.99),
				new Product("Product 2", 20.49)
		);

		User testUser2 = new User("User 2", "Password456", "User2@gmail.com", new Address("Testovacieho 23", "Bratislava", "851 01"));
		List<Product> testProducts2 = Arrays.asList(
				new Product("Product 3", 12.99),
				new Product("Product 4", 26.99)
		);

		List<Cart> cartList = Arrays.asList(
				new Cart(testUser, testProducts),
				new Cart(testUser2, testProducts2)
		);
		when(cartRepository.findAll()).thenReturn(cartList);
		List<Cart> allCarts = cartService.getAllCarts();

		assertNotNull(allCarts);
		assertEquals(cartList.size(), allCarts.size());
	}

	@Test
	public void testGetCartById() {
		long cartIdToFind = 1L;

		User testUser = new User("User 1", "Password123", "User@gmail.com", new Address("Testovacieho 11", "Košice", "040 01"));
		List<Product> testProducts = Arrays.asList(
				new Product("Product 1", 10.99),
				new Product("Product 2", 20.49)
		);

		Cart foundCart = new Cart(testUser, testProducts);
		when(cartRepository.findById(cartIdToFind)).thenReturn(Optional.of(foundCart));
		Optional<Cart> cartById = cartService.getCartById(cartIdToFind);

		assertTrue(cartById.isPresent());
		assertEquals(foundCart, cartById.get());
	}
}
