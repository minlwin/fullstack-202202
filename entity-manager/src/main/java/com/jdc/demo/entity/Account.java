package com.jdc.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ExcludeDefaultListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
@ExcludeDefaultListeners
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int balance;

	private LocalDateTime creation;
	private LocalDateTime modification;
	
	@PrePersist
	public void beforeCreate() {
		creation = LocalDateTime.now();
		System.out.println("Create Account");
	}
	
	@PreUpdate
	public void beforeUpdate() {
		modification = LocalDateTime.now();
		System.out.println("Update Account");
	}

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

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}
