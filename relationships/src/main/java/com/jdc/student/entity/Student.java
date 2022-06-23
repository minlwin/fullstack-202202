package com.jdc.student.entity;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")
@DiscriminatorValue("C")
public class Student extends Account {

	private static final long serialVersionUID = 1L;

	public Student() {
		setRole(Role.Student);
	}

	public Student(String name, String loginId, String password) {
		super(name, loginId, password);
		setRole(Role.Student);
	}

	@OneToOne(mappedBy = "student")
	private Address address;

	private LocalDate entranceDate;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public LocalDate getEntranceDate() {
		return entranceDate;
	}

	public void setEntranceDate(LocalDate entranceDate) {
		this.entranceDate = entranceDate;
	}

}
