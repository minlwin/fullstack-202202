package com.jdc.balance.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.model.domain.entity.User;
import com.jdc.balance.model.domain.form.SignUpForm;
import com.jdc.balance.model.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public void singUp(SignUpForm form) {
		form.setPassword(passwordEncoder.encode(form.getPassword()));
		userRepo.save(new User(form));
	}

}
