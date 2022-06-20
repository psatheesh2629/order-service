package com.infy.ms.order.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Orders implements Serializable {

	private static final long serialVersionUID = -1931970626644022633L;

	@Id
	@GeneratedValue
	private Integer orderId;
	private String productName;
	private Integer productQuantity;
	private Integer totalPrice;
	private Boolean paymentStatus;

	public Orders(Integer orderId, String productName, Integer productQuantity, Integer totalPrice,
			Boolean paymentStatus) {
		super();
		this.orderId = orderId;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.totalPrice = totalPrice;
		this.paymentStatus = paymentStatus;
	}

	public Orders() {
		super();
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Boolean getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", productName=" + productName + ", productQuantity=" + productQuantity
				+ ", totalPrice=" + totalPrice + "]";
	}

}
