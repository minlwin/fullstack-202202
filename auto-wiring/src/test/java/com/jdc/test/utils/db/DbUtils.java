package com.jdc.test.utils.db;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class DbUtils {
	
	@Autowired
	private DataSource testDs;
	
	public DbUtils() {
	}
	
	public DbUtils(DataSource testDs) {
		this.testDs = testDs;
	}

	public void setTestDs(DataSource testDs) {
		this.testDs = testDs;
	}
	
	public void truncate(String ... tables) {
		
		try(var conn = testDs.getConnection();
				var stmt = conn.createStatement()) {
			
			stmt.execute("set foreign_key_checks = 0");
			
			for(var table : tables) {
				stmt.execute("truncate table %s".formatted(table));
			}
			
			stmt.execute("set foreign_key_checks = 1");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void execute(String ... statements) {
		
		try(var conn = testDs.getConnection();
				var stmt = conn.createStatement()) {
			
			for(var statement : statements) {
				stmt.addBatch(statement);
			}
			
			stmt.executeBatch();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
