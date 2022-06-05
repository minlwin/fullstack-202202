package com.jdc.balance.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.balance.model.domain.entity.User;
import com.jdc.balance.model.domain.entity.User.Role;
import com.jdc.balance.model.domain.form.SignUpForm;
import com.jdc.balance.model.domain.vo.UserVo;
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

	public UserVo findByLoginId(String username) {
		return userRepo.findOneByLoginId(username).map(UserVo::new).orElseThrow();
	}

	@Transactional
	public void updateContact(String username, String phone, String email) {

		userRepo.findOneByLoginId(username)
			.ifPresent(user -> {
				user.setPhone(phone);
				user.setEmail(email);
			});
	}

	public List<UserVo> search(Boolean status, String name, String phone) {
		
		Specification<User> spec = (root, query, builder) -> builder.equal(root.get("role"), Role.Member);
		
		if(null != status) {
			spec = spec.and((root, query, builder) -> builder.equal(root.get("active"), status));
		}
		
		if(StringUtils.hasLength(name)) {
			spec = spec.and((root, query, builder) -> 
				builder.like(builder.lower(root.get("name")), name.toLowerCase().concat("%")));
		}
		
		if(StringUtils.hasLength(phone)) {
			spec = spec.and((root, query, builder) -> 
				builder.like(root.get("phone"), phone.concat("%")));
		}
		

		return userRepo.findAll(spec).stream().map(UserVo::new).toList();
	}

	@Transactional
	public void changeStatus(int id, boolean status) {
		userRepo.findById(id).ifPresent(user -> user.setActive(status));
	}
	

}
