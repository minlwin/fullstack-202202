package com.jdc.student.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "office")
public class Office extends Account{

	private static final long serialVersionUID = 1L;

	private boolean admin;

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
