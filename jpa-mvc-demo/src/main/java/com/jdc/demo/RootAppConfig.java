package com.jdc.demo;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.jdc.demo.model.repo")
public class RootAppConfig {

	@Bean
	DataSource dataSource() {
		var ds = new BasicDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/webjpa_db");
		ds.setUsername("webjpa");
		ds.setPassword("webjpa");
		return ds;
	}
	
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
		var bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource);
		bean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		bean.setPackagesToScan("com.jdc.demo.model.entity");
		bean.setJpaPropertyMap(Map.of(
				Environment.HBM2DDL_DATABASE_ACTION, "drop-and-create",
				Environment.SHOW_SQL, true,
				Environment.FORMAT_SQL, true,
				Environment.HBM2DDL_LOAD_SCRIPT_SOURCE, "division.sql"
		));
		return bean;
	}
	
	@Bean
	TransactionManager transactionManager() {
		return new JpaTransactionManager();
	}
}
