package com.tresin.cvproj.handmade_shop.api;

import com.tresin.cvproj.handmade_shop.dto.PaymentDTO;
import com.tresin.cvproj.handmade_shop.model.Payment;
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
 * The Payment API provides endpoints for managing payments.
 */
@Tag(name = "Payment", description = "The Payment API provides endpoints for managing payments.")
@RequestMapping("/api/v1/payments")
public interface PaymentApi {

	/**
	 * Creates a new payment.
	 *
	 * @param paymentDTO The DTO containing the payment information to be created.
	 * @return ResponseEntity<Payment> The created payment.
	 */
	@Operation(
			summary = "Create payment",
			description = "Creates a new payment."
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid data provided"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping
	ResponseEntity<Payment> createPayment(@Valid @RequestBody PaymentDTO paymentDTO);

	/**
	 * Updates an existing payment.
	 *
	 * @param id         The ID of the payment to update.
	 * @param paymentDTO The DTO containing the updated payment information.
	 * @return ResponseEntity<Payment> The updated payment.
	 */
	@Operation(
			summary = "Update payment",
			description = "Updates an existing payment by its ID."
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid data provided"),
			@ApiResponse(responseCode = "404", description = "Payment not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PutMapping("/{id}")
	ResponseEntity<Payment> updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentDTO paymentDTO);

	/**
	 * Deletes an existing payment.
	 *
	 * @param id The ID of the payment to delete.
	 * @return ResponseEntity<Void> No content in the response body.
	 */
	@Operation(
			summary = "Delete payment",
			description = "Deletes an existing payment by its ID."
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "Payment not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deletePayment(@PathVariable Long id);

	/**
	 * Retrieves all payments.
	 *
	 * @return ResponseEntity<List<Payment>> A list containing all payments.
	 */
	@Operation(
			summary = "Get all payments",
			description = "Retrieves all payments."
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping
	ResponseEntity<List<Payment>> getAllPayments();

	/**
	 * Retrieves a payment by its ID.
	 *
	 * @param id The ID of the payment to retrieve.
	 * @return ResponseEntity<Payment> The payment with the given ID.
	 */
	@Operation(
			summary = "Get payment by ID",
			description = "Retrieves a payment by its ID."
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "Payment not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/{id}")
	ResponseEntity<Payment> getPaymentById(@PathVariable Long id);

	/**
	 * Retrieves payments associated with a specific user.
	 *
	 * @param userId The ID of the user whose payments are to be retrieved.
	 * @return ResponseEntity<List<Payment>> The payments associated with the given user ID.
	 */
	@Operation(
			summary = "Get payments by user ID",
			description = "Retrieves payments associated with a specific user."
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid user ID provided"),
			@ApiResponse(responseCode = "404", description = "User or Payment not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/user/{userId}")
	ResponseEntity<List<Payment>> getPaymentsByUserId(@PathVariable Long userId);

	/**
	 * Retrieves a payment associated with a specific order.
	 *
	 * @param orderId The ID of the order whose payment is to be retrieved.
	 * @return ResponseEntity<Payment> The payment associated with the given order ID.
	 */
	@Operation(
			summary = "Get payment by order ID",
			description = "Retrieves a payment associated with a specific order."
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid order ID provided"),
			@ApiResponse(responseCode = "404", description = "Order or Payment not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/order/{orderId}")
	ResponseEntity<Payment> getPaymentByOrderId(@PathVariable Long orderId);
}
