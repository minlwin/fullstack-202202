package com.jdc.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.support.GenericApplicationContext;

public class Books {

	@Test
	void test() {
		
		try(var context = new GenericApplicationContext()) {
			
			var hello = new GenericBeanDefinition();
			hello.setBeanClass(String.class);
			
			context.registerBeanDefinition("helloBean", hello);
			
			context.refresh();
			
			var bean = context.getBean("helloBean");
			
			Assertions.assertNotNull(bean);
		}
		
	}
}
