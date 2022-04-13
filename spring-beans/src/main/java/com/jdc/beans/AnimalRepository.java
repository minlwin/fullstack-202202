package com.jdc.beans;

import org.springframework.stereotype.Component;

@Component
public class AnimalRepository {

	public AnimalRepository(String str) {
		System.out.println("Constructor with Argument");
	}

	public AnimalRepository() {
		System.out.println("Default Constructor");
	}

}
