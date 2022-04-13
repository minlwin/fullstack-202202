package com.jdc.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jdc.beans.AnimalRepository;

public class AnnotationTest {

	@Test
	void test() {

		try (var context = new AnnotationConfigApplicationContext()) {

			var str = new GenericBeanDefinition();
			var arg = new ConstructorArgumentValues();
			arg.addGenericArgumentValue("Hello Spring Argument.");
			str.setBeanClass(String.class);
			str.setConstructorArgumentValues(arg);
			context.registerBeanDefinition("str", str);

			context.register(AnimalRepository.class);
			context.refresh();
		}
	}
}
