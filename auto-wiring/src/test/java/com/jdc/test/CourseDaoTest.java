package com.jdc.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.students.config.AppConfig;
import com.jdc.students.dao.CourseDao;
import com.jdc.students.dto.Course;
import com.jdc.students.dto.Course.Level;
import com.jdc.test.utils.TestConfig;
import com.jdc.test.utils.db.DbUtils;

@SpringJUnitConfig(
		classes = {
				AppConfig.class, TestConfig.class
		}
)
@TestMethodOrder(value = OrderAnnotation.class)
public class CourseDaoTest {
	
	@Autowired
	private DbUtils dbUtils;

	@Autowired
	private CourseDao dao;
		
	
	@BeforeEach
	void initDb() {
		dbUtils.truncate("course");
	}

	@Test
	@Order(1)
	void test_insert() {
		// Get New Course
		Course course = getJavaBasicCourse();
		
		// Insert Course
		int id = dao.insert(course);
		
		Assertions.assertEquals(1, id);
	}
	
	@Test
	@Order(2)
	void test_find_by_id() {
		
		dbUtils.execute("""
				insert into course (name, level, fees, month) values 
				('Java Basic', 0, 150000, 6)
				""");
		
		Course course = dao.findById(1);
		
		Course expected = getJavaBasicCourse();
		expected.setId(1);
		
		Assertions.assertEquals(expected, course);
	}

	private Course getJavaBasicCourse() {
		var c = new Course();
		c.setName("Java Basic");
		c.setLevel(Level.Basic);
		c.setFees(150000);
		c.setMonths(6);
		return c;
	}
}
