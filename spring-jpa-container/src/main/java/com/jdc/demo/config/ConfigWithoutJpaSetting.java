package com.jdc.demo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.jdc.demo.repo")
@EnableTransactionManagement
public class ConfigWithoutJpaSetting {
	
	@Bean
	TransactionManager transactionManager() {
		return new JpaTransactionManager();
	}
	
	@Bean
	AbstractEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
		var bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource);
		bean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		bean.setJpaProperties(jpaProperties());
		return bean;
	}
	
	Properties jpaProperties() {
		var props = new Properties();
		props.put(Environment.HBM2DDL_AUTO, "update");
		props.put(Environment.SHOW_SQL, true);
		props.put(Environment.FORMAT_SQL, true);
		return props;
	}
	
}
