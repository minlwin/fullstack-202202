package com.jdc.project.test.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.util.StringUtils;

public class CommonUtils {

	static int integer(String str) {
		if(StringUtils.hasLength(str)) {
			return Integer.parseInt(str);
		}
		
		return 0;
	}
	
	static LocalDate localDate(String str) {
		if(StringUtils.hasLength(str)) {
			return LocalDate.parse(str, DateTimeFormatter.ofPattern("yyyyMMdd"));
		}
		
		return null;
	}
}
