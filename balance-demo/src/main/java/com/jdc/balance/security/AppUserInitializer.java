package com.jdc.balance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.model.domain.entity.User;
import com.jdc.balance.model.domain.entity.User.Role;
import com.jdc.balance.model.repo.UserRepo;

@Component
public class AppUserInitializer {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepo userRepo;

	@Transactional
	@EventListener(classes = ContextRefreshedEvent.class)
	public void initializeUser() {
		if(userRepo.count() == 0) {
			var user = new User();
			user.setName("Admin User");
			user.setLoginId("admin");
			user.setPassword(passwordEncoder.encode("admin"));
			user.setRole(Role.Admin);
			user.setActive(true);
			userRepo.save(user);
		}
	}
}
