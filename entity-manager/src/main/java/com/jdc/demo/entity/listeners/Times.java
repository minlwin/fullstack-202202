package com.jdc.demo.entity.listeners;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Embeddable;

@Embeddable
public class Times implements Serializable{

	private static final long serialVersionUID = 1L;

	private LocalDateTime creation;
	private LocalDateTime modification;

	public LocalDateTime getCreation() {
		return creation;
	}

	public void setCreation(LocalDateTime creation) {
		this.creation = creation;
	}

	public LocalDateTime getModification() {
		return modification;
	}

	public void setModification(LocalDateTime modification) {
		this.modification = modification;
	}

}
