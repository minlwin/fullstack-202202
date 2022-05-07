package com.jdc.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jdc.demo.scopes.MyCustomObject;

public class ScopeTest {

	@Test
	void test() {
		
		try(var context = new AnnotationConfigApplicationContext("com.jdc.demo.scopes")) {
			
			Assertions.assertNotNull(context.getBean(MyCustomObject.class));
			
			Assertions.assertNotNull(context.getBean(MyCustomObject.class));
			
		}
	}
}
