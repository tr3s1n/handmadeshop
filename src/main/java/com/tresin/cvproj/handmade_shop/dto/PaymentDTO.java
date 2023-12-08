package com.tresin.cvproj.handmade_shop.dto;

import com.tresin.cvproj.handmade_shop.model.Order;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class PaymentDTO {

    @NotBlank(message = "Order is required")
    private Order order;
    @NotBlank(message = "Order is required")
    private String paymentMethod;
    @NotBlank(message = "Order is required")
    private double amount;
    @NotBlank(message = "Order is required")
    private Date paymentDate;

    // Getters and setters
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
