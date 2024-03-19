package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.PaymentApi;
import com.tresin.cvproj.handmade_shop.dto.PaymentDTO;
import com.tresin.cvproj.handmade_shop.model.Payment;
import com.tresin.cvproj.handmade_shop.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
		Payment newPayment = new Payment();
		newPayment.setOrder(paymentDTO.getOrder());
		newPayment.setPaymentMethod(paymentDTO.getPaymentMethod());
		newPayment.setAmount(paymentDTO.getAmount());
		newPayment.setPaymentDate(paymentDTO.getPaymentDate());
		Payment createdPayment = paymentService.createPayment(newPayment);

		return ResponseEntity.ok(createdPayment);
	}

	@Override
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
	public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
		paymentService.deletePayment(id);

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Payment>> getAllPayments() {
		List<Payment> payments = paymentService.getAllPayments();

		return ResponseEntity.ok(payments);
	}

	@Override
	public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
		return paymentService.getPaymentById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<List<Payment>> getPaymentsByUserId(Long userId) {
		return null;
	}

	@Override
	public ResponseEntity<Payment> getPaymentByOrderId(Long orderId) {
		return null;
	}
}
