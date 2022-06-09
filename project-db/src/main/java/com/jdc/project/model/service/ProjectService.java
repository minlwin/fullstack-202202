package com.jdc.project.model.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.project.model.dto.Project;
import com.jdc.project.model.service.utils.ProjectHelper;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectHelper projectHelper;
	
	public int create(Project project) {
		// TODO Clear all test for create method
		projectHelper.validate(project);
		return 0;
	}

	public Project findById(int id) {
		// TODO Clear all test for create method
		return null;
	}

	public List<Project> search(String project, String manager, LocalDate dateFrom, LocalDate dateTo) {
		// TODO Clear all test for create method
		return null;
	}

	public int update(int id, String name, String description, LocalDate startDate, int month) {
		// TODO Clear all test for create method
		return 0;
	}

	public int deleteById(int id) {
		// TODO Clear all test for create method
		return 0;
	}

}
