package com.jdc.project.model;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@Configuration
@PropertySource("classpath:db-config.properties")
public class ProjectDbConfiguration {

	@Bean
	DataSource dataSource(
			@Value("${db.url}") String url, 
			@Value("${db.username}") String user, 
			@Value("${db.password}") String password) {
		
		var ds = new BasicDataSource();
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		return ds;
	}
	
	@Bean
	@Scope("prototype")
	NamedParameterJdbcTemplate template(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Bean
	SimpleJdbcInsert memberInsert(DataSource dataSource) {
		var insert = new SimpleJdbcInsert(dataSource);
		insert.setTableName("member");
		insert.setGeneratedKeyName("id");
		return insert;
	}

}
