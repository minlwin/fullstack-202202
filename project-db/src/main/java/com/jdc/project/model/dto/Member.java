package com.jdc.project.model.dto;

public class Member implements MemberVO{

	private int id;
	private String name;
	private String loginId;
	private String password;
	private Role role;
	private boolean active;
	
	public Member() {
	}
	
	public Member(String name, String loginId, String password, Role role, boolean active) {
		super();
		this.name = name;
		this.loginId = loginId;
		this.password = password;
		this.role = role;
		this.active = active;
	}


	public enum Role {
		Admin, Member
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

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", loginId=" + loginId + ", password=" + password + ", role="
				+ role + ", active=" + active + "]";
	}

}
