package com.tresin.cvproj.handmade_shop.api;

import com.tresin.cvproj.handmade_shop.dto.OrderDTO;
import com.tresin.cvproj.handmade_shop.model.Order;
import com.tresin.cvproj.handmade_shop.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Order", description = "the Order Api")
@RequestMapping("/api/v1/orders")
public interface OrderApi {

	@Operation(
			summary = "Create order",
			description = "Creates a new order")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@PostMapping
	ResponseEntity<Order> createOrder(@Valid @RequestBody OrderDTO orderDTO);

	@Operation(
			summary = "Update order",
			description = "Updates an existing order by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Order not found")
	})
	@PutMapping("/{id}")
	ResponseEntity<Order> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderDTO orderDTO);

	@Operation(
			summary = "Delete order",
			description = "Deletes an existing order by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Order not found")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteOrder(@PathVariable Long id);

	@Operation(
			summary = "Get all orders",
			description = "Retrieves all orders")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@GetMapping
	ResponseEntity<List<Order>> getAllOrders();

	@Operation(
			summary = "Get order by ID",
			description = "Retrieves an order by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Order not found")
	})
	@GetMapping("/{id}")
	ResponseEntity<Order> getOrderById(@PathVariable Long id);

	@Operation(
			summary = "Get user by order ID",
			description = "Retrieves the user associated with the order by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "user or order not found")
	})
	@GetMapping("/{id}/user")
	ResponseEntity<User> getUserByOrderId(@PathVariable Long id);
}
