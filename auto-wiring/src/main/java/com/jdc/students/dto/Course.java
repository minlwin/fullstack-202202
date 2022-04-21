package com.jdc.students.dto;

import java.util.Objects;

public class Course {

	public enum Level {
		Basic, Intermediate, Advance
	}

	private int id;
	private String name;
	private Level level;
	private int months;
	private int fees;

	@Override
	public int hashCode() {
		return Objects.hash(fees, id, level, months, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return fees == other.fees && id == other.id && level == other.level && months == other.months
				&& Objects.equals(name, other.name);
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

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public int getFees() {
		return fees;
	}

	public void setFees(int fees) {
		this.fees = fees;
	}

}
