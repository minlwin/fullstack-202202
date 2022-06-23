package com.jdc.student.entity.embeddable;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RegistrationPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "student_id")
	private int studentId;
	@Column(name = "section_id")
	private int sectionId;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sectionId, studentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrationPK other = (RegistrationPK) obj;
		return sectionId == other.sectionId && studentId == other.studentId;
	}

}
