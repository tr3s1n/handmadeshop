package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.PaymentApi;
import com.tresin.cvproj.handmade_shop.dto.PaymentDTO;
import com.tresin.cvproj.handmade_shop.model.Payment;
import com.tresin.cvproj.handmade_shop.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController implements PaymentApi {

	private final PaymentService paymentService;

	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@Override
	@PostMapping
	public ResponseEntity<Payment> createPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
		Payment newPayment = new Payment();
		newPayment.setOrder(paymentDTO.getOrder());
		newPayment.setPaymentMethod(paymentDTO.getPaymentMethod());
		newPayment.setAmount(paymentDTO.getAmount());
		newPayment.setPaymentDate(paymentDTO.getPaymentDate());
		Payment createdPayment = paymentService.createPayment(newPayment);

		return ResponseEntity.ok(createdPayment);
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentDTO paymentDTO) {
		Payment updatedPayment = new Payment();
		updatedPayment.setOrder(paymentDTO.getOrder());
		updatedPayment.setPaymentMethod(paymentDTO.getPaymentMethod());
		updatedPayment.setAmount(paymentDTO.getAmount());
		updatedPayment.setPaymentDate(paymentDTO.getPaymentDate());
		Payment resultPayment = paymentService.updatePayment(id, updatedPayment);

		if (resultPayment != null) {
			return ResponseEntity.ok(resultPayment);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
		paymentService.deletePayment(id);

		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping
	public ResponseEntity<List<Payment>> getAllPayments() {
		List<Payment> payments = paymentService.getAllPayments();

		return ResponseEntity.ok(payments);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
		return paymentService.getPaymentById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

}
