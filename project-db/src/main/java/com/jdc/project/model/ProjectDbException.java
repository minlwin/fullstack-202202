package com.jdc.project.model;

public class ProjectDbException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ProjectDbException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProjectDbException(String message) {
		super(message);
	}

}
