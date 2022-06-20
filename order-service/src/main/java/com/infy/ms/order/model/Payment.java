package com.infy.ms.order.model;

import java.io.Serializable;
import java.util.Date;

public class Payment implements Serializable {

	private static final long serialVersionUID = 1662707580365751917L;

	private Integer paymentId;
	private Date paymentDate;
	private Integer orderId;
	private Integer paymentAmount;

	public Payment(Integer paymentId, Date paymentDate, Integer orderId, Integer paymentAmount) {
		super();
		this.paymentId = paymentId;
		this.paymentDate = paymentDate;
		this.orderId = orderId;
		this.paymentAmount = paymentAmount;
	}

	public Payment() {
		super();
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Integer paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", paymentDate=" + paymentDate + ", orderId=" + orderId
				+ ", paymentAmount=" + paymentAmount + "]";
	}

}
