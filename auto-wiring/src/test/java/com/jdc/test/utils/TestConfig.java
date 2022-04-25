package com.jdc.test.utils;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.jdc.test.utils.db.DbUtils;
import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
public class TestConfig {
	
	@Bean
	@Primary
	DataSource testDs() {
		var ds = new MysqlDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/student_db");
		ds.setUser("root");
		ds.setPassword("admin");
		return ds;
	}
	
	@Bean
	DbUtils dbUtils(DataSource ds) {
		return new DbUtils(ds);
	}
	
}
