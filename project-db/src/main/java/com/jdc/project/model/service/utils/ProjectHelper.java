package com.jdc.project.model.service.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jdc.project.model.dto.Project;

@Component
public class ProjectHelper {
	
	@Value("${project.empty.name}")
	private String noName;
	@Value("${project.empty.manager}")
	private String noManager;
	@Value("${project.empty.start}")
	private String noStartDate;	

		
	public void validate(Project dto) {
		// TODO 
	}

	public Map<String, Object> insertParams(Project dto) {
		// TODO
		return null;
	}
}
