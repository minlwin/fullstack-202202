package com.jdc.balance.model.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserAccessLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private Type type;
	private LocalDateTime accessTime;
	private String errorMessage;

	public UserAccessLog() {
	}

	public UserAccessLog(String username, Type type, LocalDateTime accessTime) {
		super();
		this.username = username;
		this.type = type;
		this.accessTime = accessTime;
	}

	public UserAccessLog(String username, Type type, LocalDateTime accessTime, String errorMessage) {
		super();
		this.username = username;
		this.type = type;
		this.accessTime = accessTime;
		this.errorMessage = errorMessage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public LocalDateTime getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(LocalDateTime accessTime) {
		this.accessTime = accessTime;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public enum Type {
		Singin, Singout, Error
	}
}
