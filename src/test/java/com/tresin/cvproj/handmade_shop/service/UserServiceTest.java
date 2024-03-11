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
		User user = new User("User 1", "Password123", "User@gmail.com", new Address("Testovacieho 11", "Košice", "040 01"));
		when(userRepository.save(any(User.class))).thenReturn(user);
		User createdUser = userService.createUser(user);

		assertNotNull(createdUser);
	}

	@Test
	public void testDeleteUser() {
		long userIdToDelete = 1L;
		User userToDelete = new User("User 1", "Password123", "User@gmail.com", new Address("Testovacieho 11", "Košice", "040 01"));
		when(userRepository.findById(userIdToDelete)).thenReturn(Optional.of(userToDelete));
		userService.deleteUser(userIdToDelete);

		verify(userRepository, times(1)).deleteById(userIdToDelete);
	}

	@Test
	public void testUpdateUser() {
		long userIdToUpdate = 1L;
		User existingUser = new User("User 1", "Password123", "User@gmail.com", new Address("Testovacieho 11", "Košice", "040 01"));
		User updatedUserData = new User("User 4848", "123Password456", "User4848@gmail.com", new Address("Testovacieho 484", "Bratislava", "051 59"));
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
		List<User> userList = Arrays.asList(
				new User("User 1", "Password123", "User@gmail.com", new Address("Testovacieho 11", "Košice", "040 01")),
				new User("User 4848", "123Password456", "User4848@gmail.com", new Address("Testovacieho 484", "Bratislava", "051 59"))
		);
		when(userRepository.findAll()).thenReturn(userList);
		List<User> allUsers = userService.getAllUsers();

		assertNotNull(allUsers);
		assertEquals(userList.size(), allUsers.size());
	}

	@Test
	public void testGetUserById() {
		long userIdToFind = 1L;
		User foundUser = new User("User 1", "Password123", "User@gmail.com", new Address("Testovacieho 11", "Košice", "040 01"));
		when(userRepository.findById(userIdToFind)).thenReturn(Optional.of(foundUser));
		Optional<User> userById = userService.getUserById(userIdToFind);

		assertTrue(userById.isPresent());
		assertEquals(foundUser, userById.get());
	}
}
