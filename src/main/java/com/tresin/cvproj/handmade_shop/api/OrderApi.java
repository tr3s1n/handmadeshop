package com.tresin.cvproj.handmade_shop.api;

import com.tresin.cvproj.handmade_shop.dto.OrderDTO;
import com.tresin.cvproj.handmade_shop.model.Order;
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

/**
 * The Order API provides endpoints for managing orders.
 */
@Tag(name = "Order", description = "The Order API provides endpoints for managing orders.")
@RequestMapping("/api/v1/orders")
public interface OrderApi {

	/**
	 * Creates a new order.
	 *
	 * @param orderDTO The DTO containing the order information to be created.
	 * @return ResponseEntity<Order> The created order.
	 */
	@Operation(
			summary = "Create order",
			description = "Creates a new order")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid data provided"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping
	ResponseEntity<Order> createOrder(@Valid @RequestBody OrderDTO orderDTO);

	/**
	 * Updates an existing order.
	 *
	 * @param id        The ID of the order to update.
	 * @param orderDTO  The DTO containing the updated order information.
	 * @return ResponseEntity<Order> The updated order.
	 */
	@Operation(
			summary = "Update order",
			description = "Updates an existing order by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid data provided"),
			@ApiResponse(responseCode = "404", description = "Order not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PutMapping("/{id}")
	ResponseEntity<Order> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderDTO orderDTO);

	/**
	 * Deletes an existing order.
	 *
	 * @param id The ID of the order to delete.
	 * @return ResponseEntity<Void> No content in the response body.
	 */
	@Operation(
			summary = "Delete order",
			description = "Deletes an existing order by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "Order not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteOrder(@PathVariable Long id);

	/**
	 * Retrieves all orders.
	 *
	 * @return ResponseEntity<List<Order>> A list containing all orders.
	 */
	@Operation(
			summary = "Get all orders",
			description = "Retrieves all orders")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping
	ResponseEntity<List<Order>> getAllOrders();

	/**
	 * Retrieves an order by its ID.
	 *
	 * @param id The ID of the order to retrieve.
	 * @return ResponseEntity<Order> The order with the given ID.
	 */
	@Operation(
			summary = "Get order by ID",
			description = "Retrieves an order by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "Order not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/{id}")
	ResponseEntity<Order> getOrderById(@PathVariable Long id);

	/**
	 * Retrieves orders associated with a specific user.
	 *
	 * @param userId The ID of the user whose orders are to be retrieved.
	 * @return ResponseEntity<List<Order>> The list of orders associated with the given user ID.
	 */
	@Operation(
			summary = "Get orders by user ID",
			description = "Retrieves orders associated with a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid user ID provided"),
			@ApiResponse(responseCode = "404", description = "User not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/user/{userId}")
	ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId);

	/**
	 * Retrieves an order associated with a specific payment.
	 *
	 * @param paymentId The ID of the payment associated with the order.
	 * @return ResponseEntity<Order> The order associated with the given payment ID.
	 */
	@Operation(
			summary = "Get order by payment ID",
			description = "Retrieves an order associated with a specific payment"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid payment ID provided"),
			@ApiResponse(responseCode = "404", description = "Order not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/payment/{paymentId}")
	ResponseEntity<Order> getOrderByPaymentId(@PathVariable Long paymentId);

	/**
	 * Retrieves the total count of orders for a specific product.
	 *
	 * @param productId The ID of the product.
	 * @return ResponseEntity<Integer> The total count of orders for the specified product.
	 */
	@Operation(
			summary = "Get total count of orders for a product",
			description = "Retrieves the total count of orders for a specific product"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid product ID provided"),
			@ApiResponse(responseCode = "404", description = "Product not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/product/{productId}/count")
	ResponseEntity<Integer> getOrderCountByProductId(@PathVariable Long productId);
}

