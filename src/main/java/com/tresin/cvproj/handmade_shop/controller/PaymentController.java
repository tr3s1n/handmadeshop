package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.PaymentApi;
import com.tresin.cvproj.handmade_shop.dto.PaymentDTO;
import com.tresin.cvproj.handmade_shop.model.Payment;
import com.tresin.cvproj.handmade_shop.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController implements PaymentApi {

	private final PaymentService paymentService;

	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@Override
	public ResponseEntity<Payment> createPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
		Payment createdPayment = paymentService.createPayment(paymentDTO.toPayment());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
	}

	@Override
	public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentDTO paymentDTO) {
		Payment updatedPayment = paymentService.createPayment(paymentDTO.toPayment());
		Payment resultPayment = paymentService.updatePayment(id, updatedPayment);
		return ResponseEntity.ok(resultPayment);
	}

	@Override
	public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
		paymentService.deletePayment(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Payment>> getAllPayments() {
		return ResponseEntity.ok(paymentService.getAllPayments());
	}

	@Override
	public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
		return ResponseEntity.ok(paymentService.getPaymentById(id).orElseThrow());
	}

	@Override
	public ResponseEntity<List<Payment>> getPaymentsByUserId(@PathVariable Long userId) {
		return ResponseEntity.ok(paymentService.getPaymentsByUserId(userId));
	}

	@Override
	public ResponseEntity<Payment> getPaymentByOrderId(@PathVariable Long orderId) {
		return ResponseEntity.ok(paymentService.getPaymentByOrderId(orderId).orElseThrow());
	}
}
