package com.jdc.project.model.dto;

import java.time.LocalDate;

public class Task {

	private int id;
	private Phase phase;
	private String task;
	private LocalDate start;
	private int days;
	private int projectId;
	private String projectName;
	private int managerId;
	private int managerName;
	private int managerLogin;

	public enum Phase {
		Analysis, Design, Coding, Testing, Release
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

}
