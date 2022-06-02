package com.jdc.balance.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jdc.balance.model.domain.entity.User.Role;

@Controller
public class SecurityController {
	
	@GetMapping("/")
	public String index() {
		
		var auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(null != auth && 
				auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(Role.Admin.name()) 
						|| a.getAuthority().equals(Role.Member.name()))) {
			return "redirect:/user/home";
		}
		
		return "signin";
	}

	@GetMapping("signup")
	public void loadSignUp() {

	}

	@PostMapping("signup")
	public String signUp() {
		return "redirect:/";
	}

	@PostMapping("user/changepass")
	public String changePass() {
		return "redirect:/";
	}

}