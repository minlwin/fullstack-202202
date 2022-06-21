package com.jdc.student.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends Account{

	private static final long serialVersionUID = 1L;
	
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
