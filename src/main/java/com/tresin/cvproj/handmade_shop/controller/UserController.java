package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.UserApi;
import com.tresin.cvproj.handmade_shop.dto.UserDTO;
import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.model.Cart;
import com.tresin.cvproj.handmade_shop.model.Order;
import com.tresin.cvproj.handmade_shop.model.Review;
import com.tresin.cvproj.handmade_shop.model.Role;
import com.tresin.cvproj.handmade_shop.model.User;
import com.tresin.cvproj.handmade_shop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class UserController implements UserApi {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Override
	public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) {
		User newUser = new User();
		newUser.setUsername(userDTO.getUsername());
		newUser.setEmail(userDTO.getEmail());
		User createdUser = userService.createUser(newUser);

		return ResponseEntity.ok(createdUser);
	}

	@Override
	public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
		User updatedUser = new User();
		updatedUser.setUsername(userDTO.getUsername());
		updatedUser.setEmail(userDTO.getEmail());
		User resultUser = userService.updateUser(id, updatedUser);

		if (resultUser != null) {
			return ResponseEntity.ok(resultUser);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();

		return ResponseEntity.ok(users);
	}

	@Override
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		return userService.getUserById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<Void> updateUserPassword(Long id, String newPassword) {
		return null;
	}

	@Override
	public ResponseEntity<List<Order>> getOrdersByUserId(Long id) {
		return null;
	}

	@Override
	public ResponseEntity<List<Review>> getReviewsByUserId(Long id) {
		return null;
	}

	@Override
	public ResponseEntity<Address> getAddressByUserId(Long id) {
		return null;
	}

	@Override
	public ResponseEntity<Cart> getCartByUserId(Long id) {
		return null;
	}

	@Override
	public ResponseEntity<Void> addRoleToUser(Long userId, Long roleId) {
		return null;
	}

	@Override
	public ResponseEntity<Void> removeRoleFromUser(Long userId, Long roleId) {
		return null;
	}

	@Override
	public ResponseEntity<Set<Role>> getRolesByUserId(Long id) {
		return null;
	}

	@Override
	public ResponseEntity<User> getUserByUsername(String username) {
		return null;
	}

	@Override
	public ResponseEntity<User> getUserByEmail(String email) {
		return null;
	}

	@Override
	public ResponseEntity<Void> deleteUserReviews(Long id) {
		return null;
	}

	@Override
	public ResponseEntity<Void> deleteUserOrders(Long id) {
		return null;
	}
}
