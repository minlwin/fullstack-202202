package com.jdc.students.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.jdc.students.dao.CourseDao;
import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
@ComponentScan(basePackages = "com.jdc.students.dao")
public class AppConfig {
	
	private static final String URL = "jdbc:mysql://localhost:3306/student_db";
	private static final String USER = "student";
	private static final String PASS = "student";

	@Bean(name = "appDs")
	DataSource dataSource() {
		var ds = new MysqlDataSource();
		ds.setUrl(URL);
		ds.setUser(USER);
		ds.setPassword(PASS);
		return ds;
	}
	
	@Bean
	CourseDao dao(DataSource appDs) {
		return new CourseDao(appDs);
	}

}
