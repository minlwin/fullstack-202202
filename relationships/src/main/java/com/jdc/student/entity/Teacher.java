package com.jdc.student.entity;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
@DiscriminatorValue("B")
public class Teacher extends Account {

	private static final long serialVersionUID = 1L;

	public Teacher() {
		setRole(Role.Teacher);
	}

	public Teacher(String name, String loginId, String password) {
		super(name, loginId, password);
		setRole(Role.Teacher);
	}

	@OneToMany(mappedBy = "teacher")
	private List<Section> sections;

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

}
