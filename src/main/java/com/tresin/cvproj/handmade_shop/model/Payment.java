package com.tresin.cvproj.handmade_shop.model;

import com.tresin.cvproj.handmade_shop.dto.PaymentDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Order is required")
	@OneToOne
	private Order order;
	@NotBlank(message = "Payment method is required")
	private String paymentMethod;
	@NotBlank(message = "Amount is required")
	private double amount;
	@NotBlank(message = "Payment date is required")
	private Date paymentDate;

	public Payment(Order order, String paymentMethod, double amount, Date paymentDate) {
		this.order = order;
		this.paymentMethod = paymentMethod;
		this.amount = amount;
		this.paymentDate = paymentDate;
	}

	/**
	 * Updates the payment entity based on the provided DTO.
	 *
	 * @param paymentDTO The DTO containing the updated payment information.
	 */
	public void updateFromDTO(PaymentDTO paymentDTO) {
		this.setOrder(paymentDTO.getOrder());
		this.setPaymentMethod(paymentDTO.getPaymentMethod());
		this.setPaymentDate(paymentDTO.getPaymentDate());
		this.setAmount(paymentDTO.getAmount());
	}

	/**
	 * Converts the payment entity to a DTO.
	 *
	 * @return The DTO representation of the payment entity.
	 */
	public PaymentDTO toDTO() {
		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setOrder(this.order);
		paymentDTO.setPaymentMethod(this.paymentMethod);
		paymentDTO.setPaymentDate(this.paymentDate);
		paymentDTO.setAmount(this.amount);
		return paymentDTO;
	}
}
