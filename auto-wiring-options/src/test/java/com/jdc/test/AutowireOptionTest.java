package com.jdc.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jdc.demo.AppClient;

public class AutowireOptionTest {

	@Test
	void test() {
		
		try(var context = new AnnotationConfigApplicationContext()) {
			
			context.scan("com.jdc.demo");
			context.refresh();
			
			var bean = context.getBean(AppClient.class);
			System.out.println(bean.getService().getClass());
		}
	}
}
