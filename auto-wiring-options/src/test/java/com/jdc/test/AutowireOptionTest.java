package com.jdc.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.jdc.demo.AppClient;

public class AutowireOptionTest {

	@Test
	void test() {
		
		try(var context = new GenericXmlApplicationContext()) {
			
			context.load("classpath:app-config.xml");
			context.refresh();
			
			var bean = context.getBean(AppClient.class);
			System.out.println(bean.getService().getClass());
		}
	}
}
