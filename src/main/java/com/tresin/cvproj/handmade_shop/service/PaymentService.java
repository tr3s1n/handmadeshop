package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.exception.PaymentNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Payment;
import com.tresin.cvproj.handmade_shop.repository.PaymentRepository;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The PaymentService class provides operations for managing payments in the system.
 * It encapsulates the business logic for creating, updating, deleting, and retrieving payments.
 */
@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /**
     * Creates a new payment.
     *
     * @param newPayment The payment to be created.
     * @return The created payment.
     * @throws IllegalArgumentException If the newPayment is null.
     * @throws ConstraintViolationException  If the newPayment violates constraints specified by annotations in the Payment model class.
     */
    public Payment createPayment(Payment newPayment) {
        if (newPayment == null) {
            throw new IllegalArgumentException("Payment cannot be null");
        }

        Payment createdPayment = paymentRepository.save(newPayment);
        logger.info("Payment created successfully: {}", createdPayment);
        return createdPayment;
    }

    /**
     * Updates an existing payment.
     *
     * @param id             The ID of the payment to update.
     * @param updatedPayment The updated payment information.
     * @return The updated payment.
     * @throws PaymentNotFoundException If the payment with the given ID is not found.
     * @throws IllegalArgumentException  If the payment ID is null or invalid.
     */
    public Payment updatePayment(Long id, Payment updatedPayment) {
        if (id == null || id <= 0 || updatedPayment == null) {
            throw new IllegalArgumentException("Invalid payment or ID");
        }
        
        return paymentRepository.findById(id)
                .map(existingPayment -> {
                    existingPayment.updateFromDTO(updatedPayment.toDTO());
                    Payment savedPayment = paymentRepository.save(existingPayment);
                    logger.info("Payment with ID {} updated successfully", id);
                    return savedPayment;
                }).orElseThrow(() -> {
                    logger.error("Payment with ID {} not found in method updatePayment", id);
                    return new PaymentNotFoundException("Payment with ID " + id + " not found");
                });
    }

    /**
     * Deletes an existing payment.
     *
     * @param id The ID of the payment to delete.
     * @throws PaymentNotFoundException If the payment with the given ID is not found.
     * @throws IllegalArgumentException  If the payment ID is null or invalid.
     */
    public void deletePayment(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid payment ID");
        }

        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        paymentOptional.ifPresentOrElse(
                payment -> {
                    paymentRepository.deleteById(id);
                    logger.info("Payment with ID {} deleted successfully", id);
                },
                () -> {
                    logger.error("Payment with ID {} not found in method deletePayment", id);
                    throw new PaymentNotFoundException("Payment with ID " + id + " not found");
                }
        );
    }

    /**
     * Retrieves all payments.
     *
     * @return A list containing all payments.
     */
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    /**
     * Retrieves the payment with the specified ID.
     *
     * @param id The ID of the payment to retrieve.
     * @return An Optional containing the payment with the given ID, if present.
     * @throws PaymentNotFoundException If the payment with the given ID is not found.
     * @throws IllegalArgumentException  If the payment ID is null or invalid.
     */
    public Optional<Payment> getPaymentById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid payment ID");
        }

        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        paymentOptional.ifPresentOrElse(
                payment -> logger.info("Payment with ID {} found", id),
                () -> {
                    logger.error("Payment with ID {} not found in method getPaymentById", id);
                    throw new PaymentNotFoundException("Payment with ID " + id + " not found");
                }
        );

        return paymentOptional;
    }

    /**
     * Retrieves the payment associated with the specified order ID.
     *
     * @param orderId The ID of the order.
     * @return An Optional containing the payment associated with the order, if present.
     * @throws PaymentNotFoundException If the payment for the order with the given order ID is not found.
     * @throws IllegalArgumentException  If the order ID is null or invalid.
     */
    public Optional<Payment> getPaymentByOrderId(Long orderId) {
        if (orderId == null || orderId <= 0) {
            throw new IllegalArgumentException("Invalid order ID");
        }

        Optional<Payment> paymentOptional = paymentRepository.findByOrderId(orderId);
        paymentOptional.ifPresentOrElse(
                payment -> logger.info("Payment for order with ID {} found", orderId),
                () -> {
                    logger.error("Payment for order with ID {} not found in method getPaymentByOrderId", orderId);
                    throw new PaymentNotFoundException("Payment for order with ID " + orderId + " not found");
                }
        );

        return paymentOptional;
    }
}