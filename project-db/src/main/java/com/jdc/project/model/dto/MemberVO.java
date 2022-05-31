package com.jdc.project.model.dto;

import com.jdc.project.model.dto.Member.Role;

public interface MemberVO {
	int getId();
	String getName();
	String getLoginId();
	Role getRole();
	boolean isActive();
}
