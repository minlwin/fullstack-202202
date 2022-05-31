package com.jdc.project.test.utils;

import com.jdc.project.model.dto.Project;

public class ProjectServiceTestUtils {

	public static int id(String csv) {
		var array = csv.split(",");
		return Integer.parseInt(array[0]);
	}
	
	public static Project dto(String csv) {
		return null;
	}
}
