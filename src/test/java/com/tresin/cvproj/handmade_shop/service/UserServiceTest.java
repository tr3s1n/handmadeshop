package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.model.User;
import com.tresin.cvproj.handmade_shop.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
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
public class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	@Test
	public void testCreateUser() {
		User testUser = User.builder()
				.username("User 1")
				.password("Password123")
				.email("User@gmail.com")
				.address(new Address("Testovacieho 11", "Košice", "040 01"))
				.roles(new HashSet<>())
				.build();

		when(userRepository.save(any(User.class))).thenReturn(testUser);
		User createdUser = userService.createUser(testUser);

		assertNotNull(createdUser);
	}

	@Test
	public void testDeleteUser() {
		long userIdToDelete = 1L;

		User testUser = User.builder()
				.username("User 1")
				.password("Password123")
				.email("User@gmail.com")
				.address(new Address("Testovacieho 11", "Košice", "040 01"))
				.roles(new HashSet<>())
				.build();

		when(userRepository.findById(userIdToDelete)).thenReturn(Optional.of(testUser));
		userService.deleteUser(userIdToDelete);

		verify(userRepository, times(1)).deleteById(userIdToDelete);
	}

	@Test
	public void testUpdateUser() {
		long userIdToUpdate = 1L;

		User existingUser = User.builder()
				.username("User 1")
				.password("Password123")
				.email("User@gmail.com")
				.address(new Address("Testovacieho 11", "Košice", "040 01"))
				.roles(new HashSet<>())
				.build();

		User updatedUserData = User.builder()
				.username("User 484")
				.password("Password1234584")
				.email("Userasdas@gmail.com")
				.address(new Address("Testovacieho 15", "Košice", "040 01"))
				.roles(new HashSet<>())
				.build();

		when(userRepository.findById(userIdToUpdate)).thenReturn(Optional.of(existingUser));
		when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
		User updatedUser = userService.updateUser(userIdToUpdate, updatedUserData);

		assertNotNull(updatedUser);
		assertEquals(updatedUserData.getUsername(), updatedUser.getUsername());
		assertEquals(updatedUserData.getPassword(), updatedUser.getPassword());
		assertEquals(updatedUserData.getEmail(), updatedUser.getEmail());
		assertEquals(updatedUserData.getAddress(), updatedUser.getAddress());
	}

	@Test
	public void testGetAllUsers() {
		User testUser = User.builder()
				.username("User 1")
				.password("Password123")
				.email("User@gmail.com")
				.address(new Address("Testovacieho 11", "Košice", "040 01"))
				.roles(new HashSet<>())
				.build();

		User testUser2 = User.builder()
				.username("User 2")
				.password("Passwo84rd123")
				.email("Userasd@gmail.com")
				.address(new Address("Testovacieho 15", "Košice", "040 01"))
				.roles(new HashSet<>())
				.build();

		List<User> userList = Arrays.asList(testUser, testUser2);

		when(userRepository.findAll()).thenReturn(userList);
		List<User> allUsers = userService.getAllUsers();

		assertNotNull(allUsers);
		assertEquals(userList.size(), allUsers.size());
	}

	@Test
	public void testGetUserById() {
		long userIdToFind = 1L;

		User testUser = User.builder()
				.username("User 1")
				.password("Password123")
				.email("User@gmail.com")
				.address(new Address("Testovacieho 11", "Košice", "040 01"))
				.roles(new HashSet<>())
				.build();

		when(userRepository.findById(userIdToFind)).thenReturn(Optional.of(testUser));
		Optional<User> userById = userService.getUserById(userIdToFind);

		assertTrue(userById.isPresent());
		assertEquals(testUser, userById.get());
	}
}
