package com.jdc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.demo.model.repo.DivisionRepo;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private DivisionRepo divisionRepo;

	@GetMapping
	String index(ModelMap model) {
		model.put("divisions", divisionRepo.findAll());
		return "welcome";
	}
}
