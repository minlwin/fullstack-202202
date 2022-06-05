package com.jdc.balance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.balance.model.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class UserController {
	
	@Autowired
	private UserService service;

	@GetMapping
	public String search(
			@RequestParam(required = false) Boolean status,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String phone,
			ModelMap model
			) {
		
		var list = service.search(status, name, phone);
		model.put("list", list);
		
		return "users";
	}
	
	@PostMapping("status")
	String changeStatus(
			@RequestParam int id,
			@RequestParam boolean status
			) {
		
		service.changeStatus(id, !status);
		
		return "redirect:/admin/users";
	}

}