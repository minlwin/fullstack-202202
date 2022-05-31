package com.jdc.project.model.dto;

import java.time.LocalDate;

public class Project {

	private int id;
	private String name;
	private String description;
	private int managerId;
	private int managerName;
	private int managerLogin;
	private LocalDate startDate;
	private int months;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public int getManagerName() {
		return managerName;
	}

	public void setManagerName(int managerName) {
		this.managerName = managerName;
	}

	public int getManagerLogin() {
		return managerLogin;
	}

	public void setManagerLogin(int managerLogin) {
		this.managerLogin = managerLogin;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

}
