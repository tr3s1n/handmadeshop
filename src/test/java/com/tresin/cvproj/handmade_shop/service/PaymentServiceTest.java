package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.model.*;
import com.tresin.cvproj.handmade_shop.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
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
public class PaymentServiceTest {

	@InjectMocks
	private PaymentService paymentService;

	@Mock
	private PaymentRepository paymentRepository;

	@Test
	public void testCreatePayment() {
		User testUser = User.builder()
				.username("User 1")
				.password("Password123")
				.email("User@gmail.com")
				.address(new Address("Testovacieho 11", "Košice", "040 01"))
				.roles(new HashSet<>())
				.build();

		List<Product> testProducts = Arrays.asList(
				new Product("Product 1", 10.99),
				new Product("Product 2", 20.49));

		Order testOrder = new Order(testUser, testProducts);

		Payment payment = new Payment(testOrder, "card", 31.48, new Date(2024, 3, 11));
		when(paymentRepository.save(any(Payment.class))).thenReturn(payment);
		Payment createdPayment = paymentService.createPayment(payment);

		assertNotNull(createdPayment);
	}

	@Test
	public void testDeletePayment() {
		long paymentIdToDelete = 1L;

		User testUser = User.builder()
				.username("User 1")
				.password("Password123")
				.email("User@gmail.com")
				.address(new Address("Testovacieho 11", "Košice", "040 01"))
				.roles(new HashSet<>())
				.build();

		List<Product> testProducts = Arrays.asList(
				new Product("Product 1", 10.99),
				new Product("Product 2", 20.49));
		Order testOrder = new Order(testUser, testProducts);

		Payment paymentToDelete = new Payment(testOrder, "card", 31.48, new Date(2024, 3, 11));
		when(paymentRepository.findById(paymentIdToDelete)).thenReturn(Optional.of(paymentToDelete));
		paymentService.deletePayment(paymentIdToDelete);

		verify(paymentRepository, times(1)).deleteById(paymentIdToDelete);
	}

	@Test
	public void testUpdatePayment() {
		long paymentIdToUpdate = 1L;

		User testUser = User.builder()
				.username("User 1")
				.password("Password123")
				.email("User@gmail.com")
				.address(new Address("Testovacieho 11", "Košice", "040 01"))
				.roles(new HashSet<>())
				.build();

		List<Product> testProducts = Arrays.asList(
				new Product("Product 1", 10.99),
				new Product("Product 2", 20.49));
		Order testOrder = new Order(testUser, testProducts);

		Payment existingPayment = new Payment(testOrder, "card", 31.48, new Date(2024, 3, 11));
		Payment updatedPaymentData = new Payment(testOrder, "gpay", 31.48, new Date(2024, 3, 15));
		when(paymentRepository.findById(paymentIdToUpdate)).thenReturn(Optional.of(existingPayment));
		when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));
		Payment updatedPayment = paymentService.updatePayment(paymentIdToUpdate, updatedPaymentData);

		assertNotNull(updatedPayment);
		assertEquals(updatedPaymentData.getOrder(), updatedPayment.getOrder());
		assertEquals(updatedPaymentData.getPaymentMethod(), updatedPayment.getPaymentMethod());
		assertEquals(updatedPaymentData.getAmount(), updatedPayment.getAmount());
		assertEquals(updatedPaymentData.getPaymentDate(), updatedPayment.getPaymentDate());
	}

	@Test
	public void testGetAllPayments() {
		User testUser = User.builder()
				.username("User 1")
				.password("Password123")
				.email("User@gmail.com")
				.address(new Address("Testovacieho 11", "Košice", "040 01"))
				.roles(new HashSet<>())
				.build();

		List<Product> testProducts = Arrays.asList(
				new Product("Product 1", 10.99),
				new Product("Product 2", 20.49));
		Order testOrder = new Order(testUser, testProducts);

		List<Payment> paymentList = Arrays.asList(
				new Payment(testOrder, "card", 31.48, new Date(2024, 3, 11)),
				new Payment(testOrder, "gpay", 31.48, new Date(2024, 3, 15)));

		when(paymentRepository.findAll()).thenReturn(paymentList);
		List<Payment> allPayments = paymentService.getAllPayments();

		assertNotNull(allPayments);
		assertEquals(paymentList.size(), allPayments.size());
	}

	@Test
	public void testGetPaymentById() {
		long paymentIdToFind = 1L;

		User testUser = User.builder()
				.username("User 1")
				.password("Password123")
				.email("User@gmail.com")
				.address(new Address("Testovacieho 11", "Košice", "040 01"))
				.roles(new HashSet<>())
				.build();

		List<Product> testProducts = Arrays.asList(
				new Product("Product 1", 10.99),
				new Product("Product 2", 20.49));
		Order testOrder = new Order(testUser, testProducts);

		Payment foundPayment = new Payment(testOrder, "card", 31.48, new Date(2024, 3, 11));
		when(paymentRepository.findById(paymentIdToFind)).thenReturn(Optional.of(foundPayment));
		Optional<Payment> paymentById = paymentService.getPaymentById(paymentIdToFind);

		assertTrue(paymentById.isPresent());
		assertEquals(foundPayment, paymentById.get());
	}
}
