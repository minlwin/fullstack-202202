package com.jdc.project.model.service.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jdc.project.model.ProjectDbException;
import com.jdc.project.model.dto.Member;

@Component
public class MemberHelper {
	
	@Value("${member.empty}")
	private String emptyMember;
	@Value("${member.empty.name}")
	private String noName;
	@Value("${member.empty.login}")
	private String noLoginId;
	@Value("${member.empty.password}")
	private String noPassword;
	
	public void validate(Member dto) {
		
		if (null == dto) {
			throw new ProjectDbException(emptyMember);
		}

		if (!StringUtils.hasLength(dto.getName())) {
			throw new ProjectDbException(noName);
		}

		if (!StringUtils.hasLength(dto.getLoginId())) {
			throw new ProjectDbException(noLoginId);
		}

		if (!StringUtils.hasLength(dto.getPassword())) {
			throw new ProjectDbException(noPassword);
		}
	}

	public Map<String, Object> insertParams(Member dto) {
		var map = new HashMap<String, Object>();
		map.put("name", dto.getName());
		map.put("login_id", dto.getLoginId());
		map.put("password", dto.getPassword());
		map.put("role", dto.getRole());
		map.put("active", dto.isActive());
		return map;
	}

}
