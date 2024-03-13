package com.tresin.cvproj.handmade_shop.dto;

import com.tresin.cvproj.handmade_shop.model.Order;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class PaymentDTO {

    @NotBlank(message = "Order is required")
    private Order order;
    @NotBlank(message = "Order is required")
    private String paymentMethod;
    @NotBlank(message = "Order is required")
    private double amount;
    @NotBlank(message = "Order is required")
    private Date paymentDate;

}
