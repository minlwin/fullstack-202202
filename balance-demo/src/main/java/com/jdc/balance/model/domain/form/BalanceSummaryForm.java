package com.jdc.balance.model.domain.form;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.jdc.balance.model.domain.entity.Balance.Type;

public class BalanceSummaryForm implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	
	@NotNull(message = "Enter Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	@NotBlank(message = "Enter Category")
	private String category;
	private Type type;

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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}