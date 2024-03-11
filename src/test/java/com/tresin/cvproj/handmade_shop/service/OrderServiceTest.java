package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.model.Order;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.model.User;
import com.tresin.cvproj.handmade_shop.repository.OrderRepository;
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
public class OrderServiceTest {

	@InjectMocks
	private OrderService orderService;

	@Mock
	private OrderRepository orderRepository;

	@Test
	public void testCreateOrder() {
		User testUser = new User("User 1", "Password123", "User@gmail.com", new Address("Testovacieho 11", "Košice", "040 01"));
		List<Product> testProducts = Arrays.asList(
				new Product("Product 1", 10.99),
				new Product("Product 2", 20.49)
		);

		Order order = new Order(testUser, testProducts);
		when(orderRepository.save(any(Order.class))).thenReturn(order);
		Order createdOrder = orderService.createOrder(order);

		assertNotNull(createdOrder);
	}

	@Test
	public void testDeleteOrder() {
		long orderIdToDelete = 1L;

		User testUser = new User("User 1", "Password123", "User@gmail.com", new Address("Testovacieho 11", "Košice", "040 01"));
		List<Product> testProducts = Arrays.asList(
				new Product("Product 1", 10.99),
				new Product("Product 2", 20.49)
		);

		Order orderToDelete = new Order(testUser, testProducts);
		when(orderRepository.findById(orderIdToDelete)).thenReturn(Optional.of(orderToDelete));
		orderService.deleteOrder(orderIdToDelete);

		verify(orderRepository, times(1)).deleteById(orderIdToDelete);
	}

	@Test
	public void testUpdateOrder() {
		long orderIdToUpdate = 1L;

		User testUser = new User("User 1", "Password123", "User@gmail.com", new Address("Testovacieho 11", "Košice", "040 01"));
		List<Product> testProducts = Arrays.asList(
				new Product("Product 1", 10.99),
				new Product("Product 2", 20.49)
		);
		List<Product> testUpdatedProducts = Arrays.asList(
				new Product("Product 3", 11.99),
				new Product("Product 4", 22.69)
		);

		Order existingOrder = new Order(testUser, testProducts);
		Order updatedOrderData = new Order(testUser, testUpdatedProducts);
		when(orderRepository.findById(orderIdToUpdate)).thenReturn(Optional.of(existingOrder));
		when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));
		Order updatedOrder = orderService.updateOrder(orderIdToUpdate, updatedOrderData);

		assertNotNull(updatedOrder);
		assertEquals(updatedOrderData.getUser(), updatedOrder.getUser());
		assertEquals(updatedOrderData.getProducts(), updatedOrder.getProducts());
	}

	@Test
	public void testGetAllOrders() {
		User testUser = new User("User 1", "Password123", "User@gmail.com", new Address("Testovacieho 11", "Košice", "040 01"));
		List<Product> testProducts = Arrays.asList(
				new Product("Product 1", 10.99),
				new Product("Product 2", 20.49)
		);
		List<Product> testProducts2 = Arrays.asList(
				new Product("Product 3", 11.99),
				new Product("Product 4", 22.69)
		);

		List<Order> orderList = Arrays.asList(
				new Order(testUser, testProducts),
				new Order(testUser, testProducts2)
		);
		when(orderRepository.findAll()).thenReturn(orderList);
		List<Order> allOrders = orderService.getAllOrders();

		assertNotNull(allOrders);
		assertEquals(orderList.size(), allOrders.size());
	}

	@Test
	public void testGetOrderById() {
		long orderIdToFind = 1L;

		User testUser = new User("User 1", "Password123", "User@gmail.com", new Address("Testovacieho 11", "Košice", "040 01"));
		List<Product> testProducts = Arrays.asList(
				new Product("Product 1", 10.99),
				new Product("Product 2", 20.49)
		);

		Order foundOrder = new Order(testUser, testProducts);
		when(orderRepository.findById(orderIdToFind)).thenReturn(Optional.of(foundOrder));
		Optional<Order> orderById = orderService.getOrderById(orderIdToFind);

		assertTrue(orderById.isPresent());
		assertEquals(foundOrder, orderById.get());
	}
}
