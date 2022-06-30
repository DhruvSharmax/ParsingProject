package com.java.ordersParser.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

public class OrderDetail {
	
	private int id;
	
	@NotNull(message = "Order id cannot be null")
	@Digits(fraction = 0, integer = 1000, message = "Order id is not numeric or larger than 1000")
	private String orderId;
	
	@NotNull(message = "Amount cannot be null")
	@Digits(fraction = 2, integer = 100000, message = "Amount is not numeric or larger than 100000")
	private String amount;
	
	@NotNull(message = "Comment cannot be null")
	@Size(max = 100, message = "comment should be of maximum 100 length")
	private String comment;
	
	@NotNull(message = "Currency cannot be null")
	@Size(max = 3,message = "currency is incorrect")
	private String currency;
	
	private String filename;
	
	private int line;

	private String result;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
