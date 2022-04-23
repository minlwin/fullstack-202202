package com.jdc.students.dao;

import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.jdc.students.dto.Course;
import com.jdc.students.dto.Course.Level;

public class CourseDao {

	private static final String INSERT_SQL = """
			insert into course (name, level, month, fees) values (?, ?, ?, ?)
			""";

	private static final String FIND_BY_ID = "select * from course where id = ?";
	

	private DataSource dataSource;
	
	public CourseDao() {
	}
	
	public CourseDao(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public int insert(Course course) {

		try (var conn = dataSource.getConnection();
				var stmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, course.getName());
			stmt.setInt(2, course.getLevel().ordinal());
			stmt.setInt(3, course.getMonths());
			stmt.setInt(4, course.getFees());

			var result = stmt.executeUpdate();

			if (result > 0) {
				var resultSet = stmt.getGeneratedKeys();

				while (resultSet.next()) {
					return resultSet.getInt(1);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public Course findById(int id) {
		try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(FIND_BY_ID)) {

			stmt.setInt(1, id);

			var resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				var course = new Course();
				course.setId(resultSet.getInt("id"));
				course.setName(resultSet.getString("name"));
				course.setLevel(Level.values()[resultSet.getInt("level")]);
				course.setMonths(resultSet.getInt("month"));
				course.setFees(resultSet.getInt("fees"));

				return course;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
