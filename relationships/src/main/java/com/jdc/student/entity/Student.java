package com.jdc.student.entity;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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

	@ManyToMany
	private List<Section> classes;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Section> getClasses() {
		return classes;
	}

	public void setClasses(List<Section> classes) {
		this.classes = classes;
	}

}
