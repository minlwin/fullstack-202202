package com.jdc.project.model.dto;

import java.time.LocalDate;

import com.jdc.project.model.dto.Task.Phase;

public class Assignment {

	private int id;
	private int memberId;
	private String member;
	private String memberLogin;
	private int taskId;
	private Phase phase;
	private String task;
	private LocalDate start;
	private int days;
	private int projectId;
	private String projectName;
	private int managerId;
	private int managerName;
	private int managerLogin;

	public enum Type {
		Response, Review
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getMemberLogin() {
		return memberLogin;
	}

	public void setMemberLogin(String memberLogin) {
		this.memberLogin = memberLogin;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
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
