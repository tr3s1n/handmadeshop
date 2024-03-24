package com.tresin.cvproj.handmade_shop.dto;

import com.tresin.cvproj.handmade_shop.model.Order;
import com.tresin.cvproj.handmade_shop.model.Payment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    @NotBlank(message = "Order is required")
    private Order order;
    @NotBlank(message = "Payment method is required")
    private String paymentMethod;
    @NotBlank(message = "Amount is required")
    private double amount;
    @NotBlank(message = "Payment date is required")
    private Date paymentDate;

    /**
     * Converts the PaymentDTO object to a Payment object.
     *
     * @return The Payment object converted from the PaymentDTO.
     * @throws IllegalArgumentException If the order, amount, paymentDate or paymentMethod is null.
     */
    public Payment toPayment() {
        if (this.order == null || this.paymentMethod == null || this.amount <= 0 || this.paymentDate == null) {
            throw new IllegalArgumentException("Invalid PaymentDTO: Order, amount, paymentMethod and paymentDate are required");
        }

        Payment payment = new Payment();
        payment.setOrder(this.getOrder());
        payment.setAmount(this.getAmount());
        payment.setPaymentDate(this.getPaymentDate());
        payment.setPaymentMethod(this.getPaymentMethod());
        return payment;
    }
}
