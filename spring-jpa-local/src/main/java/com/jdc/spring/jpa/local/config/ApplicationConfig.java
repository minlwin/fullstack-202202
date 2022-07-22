package com.jdc.spring.jpa.local.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.jdc.spring.jpa.local.repo")
@EnableTransactionManagement
public class ApplicationConfig {
	
	@Bean
	AbstractEntityManagerFactoryBean entityManagerFactoryBean() {
		var bean = new LocalEntityManagerFactoryBean();
		bean.setPersistenceUnitName("spring-jpa-local");
		return bean;
	}
	
	@Bean
	TransactionManager transactionManager() {
		return new JpaTransactionManager();
	}

}
