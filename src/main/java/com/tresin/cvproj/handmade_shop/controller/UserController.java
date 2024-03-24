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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
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
		User createdUser = userService.createUser(userDTO.toUser());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}

	@Override
	public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
		User updatedUser = userService.createUser(userDTO.toUser());
		User resultUser = userService.updateUser(id, updatedUser);
		return ResponseEntity.ok(resultUser);
	}

	@Override
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@Override
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getUserById(id).orElseThrow());
	}

	@Override
	public ResponseEntity<Void> updateUserPassword(@PathVariable Long id, @PathVariable String newPassword) {
		return ResponseEntity.ok(userService.updateUserPassword(id, newPassword));
	}

	@Override
	public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getOrdersByUserId(id));
	}

	@Override
	public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getReviewsByUserId(id));
	}

	@Override
	public ResponseEntity<Address> getAddressByUserId(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getAddressByUserId(id).orElseThrow());
	}

	@Override
	public ResponseEntity<Cart> getCartByUserId(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getCartByUserId(id).orElseThrow());
	}

	@Override
	public ResponseEntity<Void> addRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
		return ResponseEntity.ok(userService.addRoleToUser(userId, roleId));
	}

	@Override
	public ResponseEntity<Void> removeRoleFromUser(@PathVariable Long userId, @PathVariable Long roleId) {
		return ResponseEntity.ok(userService.removeRolFromUser(userId, roleId));
	}

	@Override
	public ResponseEntity<Set<Role>> getRolesByUserId(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getRolesByUserId(id));
	}

	@Override
	public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
		return ResponseEntity.ok(userService.getUserByUsername(username).orElseThrow());
	}

	@Override
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
		return ResponseEntity.ok(userService.getUserByEmail(email).orElseThrow());
	}

	@Override
	public ResponseEntity<Void> deleteUserReviews(@PathVariable Long id) {
		return ResponseEntity.ok(userService.deleteUserReviews(id));
	}

	@Override
	public ResponseEntity<Void> deleteUserOrders(@PathVariable Long id) {
		return ResponseEntity.ok(userService.deleteUserOrders(id));
	}
}
