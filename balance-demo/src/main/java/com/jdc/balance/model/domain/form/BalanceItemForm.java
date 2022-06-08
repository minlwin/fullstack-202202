package com.jdc.balance.model.domain.form;

import java.io.Serializable;

public class BalanceItemForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String item;
	private int unitPrice;
	private int quantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}