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

@Tag(name = "Payment", description = "the Payment Api")
@RequestMapping("/api/v1/payments")
public interface PaymentApi {

	@Operation(
			summary = "Create payment",
			description = "Creates a new payment")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@PostMapping
	ResponseEntity<Payment> createPayment(@Valid @RequestBody PaymentDTO paymentDTO);

	@Operation(
			summary = "Update payment",
			description = "Updates an existing payment by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Payment not found")
	})
	@PutMapping("/{id}")
	ResponseEntity<Payment> updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentDTO paymentDTO);

	@Operation(
			summary = "Delete payment",
			description = "Deletes an existing payment by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Payment not found")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deletePayment(@PathVariable Long id);

	@Operation(
			summary = "Get all payments",
			description = "Retrieves all payments")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@GetMapping
	ResponseEntity<List<Payment>> getAllPayments();

	@Operation(
			summary = "Get payment by ID",
			description = "Retrieves an payment by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Payment not found")
	})
	@GetMapping("/{id}")
	ResponseEntity<Payment> getPaymentById(@PathVariable Long id);

	@Operation(
			summary = "Get payments by user ID",
			description = "Retrieves payments associated with a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@GetMapping("/user/{userId}")
	ResponseEntity<List<Payment>> getPaymentsByUserId(@PathVariable Long userId);

	@Operation(
			summary = "Get payment by order ID",
			description = "Retrieves a payment associated with a specific order"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Payment not found")
	})
	@GetMapping("/order/{orderId}")
	ResponseEntity<Payment> getPaymentByOrderId(@PathVariable Long orderId);
}
