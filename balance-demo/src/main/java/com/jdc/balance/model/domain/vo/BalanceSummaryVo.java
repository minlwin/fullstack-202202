package com.jdc.balance.model.domain.vo;

import java.time.LocalDate;

import com.jdc.balance.model.domain.entity.Balance.Type;

public class BalanceSummaryVo {

	private int id;
	private LocalDate date;
	private Type type;
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}