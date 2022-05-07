package com.jdc.demo.scopes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("custom")
public class MyCustomObject {

	public MyCustomObject() {
		System.out.println("MyCustomObject is initialized.");
	}
}
