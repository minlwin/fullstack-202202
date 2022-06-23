package com.jdc.student.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "office")
@DiscriminatorValue("A")
public class Office extends Account{

	private static final long serialVersionUID = 1L;
	
	public Office() {
		setRole(Role.Office);
	}
	
	public Office(String name, String loginId, String password) {
		super(name, loginId, password);
		setRole(Role.Office);
	}

	private boolean admin;

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
