package com.jdc.project.model.dto;

import java.time.LocalDate;

public class Project {

	private int id;
	private String name;
	private String description;
	private int managerId;
	private String managerName;
	private String managerLogin;
	private LocalDate startDate;
	private int months;

	public Project() {
	}

	public Project(String name, String description, int managerId, LocalDate startDate, int months) {
		super();
		this.name = name;
		this.description = description;
		this.managerId = managerId;
		this.startDate = startDate;
		this.months = months;
	}

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

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerLogin() {
		return managerLogin;
	}

	public void setManagerLogin(String managerLogin) {
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
