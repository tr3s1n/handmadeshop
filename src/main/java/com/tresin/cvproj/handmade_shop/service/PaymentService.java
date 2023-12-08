package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.exception.PaymentNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Payment;
import com.tresin.cvproj.handmade_shop.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    public Payment createPayment(Payment newPayment) {
        Payment createdPayment = paymentRepository.save(newPayment);
        logger.info("Payment with ID {} created successfully", createdPayment.getId());
        return createdPayment;
    }

    public Payment updatePayment(Long paymentId, Payment updatedPayment) {
        Optional<Payment> existingPaymentOptional = paymentRepository.findById(paymentId);
        if (existingPaymentOptional.isPresent()) {
            Payment existingPayment = existingPaymentOptional.get();
            existingPayment.setOrder(existingPayment.getOrder());
            existingPayment.setPaymentMethod(updatedPayment.getPaymentMethod());
            existingPayment.setAmount(updatedPayment.getAmount());
            existingPayment.setPaymentDate(updatedPayment.getPaymentDate());

            Payment savedPayment = paymentRepository.save(existingPayment);
            logger.info("Payment with ID {} updated successfully", paymentId);

            return savedPayment;
        } else {
            logger.warn("Payment with ID {} not found in method updatePayment", paymentId);
            throw new PaymentNotFoundException("Payment with ID " + paymentId + " not found");
        }
    }

    public void deletePayment(Long paymentId) {
        Optional<Payment> existingPaymentOptional = paymentRepository.findById(paymentId);
        if (existingPaymentOptional.isPresent()) {
            paymentRepository.deleteById(paymentId);
            logger.info("Payment with ID {} deleted successfully", paymentId);
        } else {
            logger.warn("Payment with ID {} not found in method deletePayment", paymentId);
            throw new PaymentNotFoundException("Payment with ID " + paymentId + " not found");
        }
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long paymentId) {
        Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);
        if (paymentOptional.isPresent()) {
            logger.info("Payment with ID {} found", paymentId);
        } else {
            logger.warn("Payment with ID {} not found in method getPaymentById", paymentId);
            throw new PaymentNotFoundException("Payment with ID " + paymentId + " not found");
        }
        return paymentOptional;
    }

}
