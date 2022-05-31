package com.jdc.project.test.utils;

import static com.jdc.project.test.utils.CommonUtils.*;
import com.jdc.project.model.dto.Project;

public class ProjectServiceTestUtils {

	public static int id(String csv) {
		var array = csv.split(",");
		return Integer.parseInt(array[0]);
	}
	
	public static Project dto(String csv) {
		
		var array = csv.split(",");
		
		if(array.length == 6) {
			return new Project(
					array[1], 
					array[2], 
					integer(array[3]), 
					localDate(array[4]), 
					integer(array[5]));
		}
		
		return new Project(
				array[0], 
				array[1], 
				integer(array[2]), 
				localDate(array[3]), 
				integer(array[4]));
	}
	
}
