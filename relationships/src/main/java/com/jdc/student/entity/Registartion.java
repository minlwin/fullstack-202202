package com.jdc.student.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.jdc.student.entity.embeddable.RegistrationPK;

@Entity
@Table(name = "registration")
public class Registartion implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RegistrationPK id;

	@ManyToOne
	@MapsId("studentId")
	private Student student;

	@ManyToOne
	@MapsId("sectionId")
	private Section section;
	
	private LocalDate registrationDate;
	private int paidFees;

	public RegistrationPK getId() {
		return id;
	}

	public void setId(RegistrationPK id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public int getPaidFees() {
		return paidFees;
	}

	public void setPaidFees(int paidFees) {
		this.paidFees = paidFees;
	}

}
