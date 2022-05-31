package com.jdc.balance.model.domain.form;

import java.time.LocalDate;

public class BalanceSummaryForm {

	private int id;
	private LocalDate date;
	private String category;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}