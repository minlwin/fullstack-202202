package com.jdc.project.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jdc.project.model.ProjectDbException;
import com.jdc.project.model.dto.Member;
import com.jdc.project.model.dto.Member.Role;
import com.jdc.project.model.dto.MemberVO;
import com.jdc.project.model.service.utils.MemberHelper;

@Service
public class MemberService {

	@Autowired
	private SimpleJdbcInsert memberInsert;
	@Autowired
	private NamedParameterJdbcTemplate template;
	@Autowired
	private MemberHelper helper;
	
	@Value("${member.duplicate.login}")
	private String duplicatePassword;


	private RowMapper<Member> rowMapper;
	

	public MemberService() {
		rowMapper = new BeanPropertyRowMapper<>(Member.class);
	}

	public int create(Member dto) {
		try {
			// Validate Inputs
			helper.validate(dto);
			
			// Get Insert Parameters
			var params = helper.insertParams(dto);
			
			// Insert into DB
			var result = memberInsert.executeAndReturnKey(params);
			
			// Return ID
			return result.intValue();
		} catch (DuplicateKeyException e) {
			throw new ProjectDbException(duplicatePassword, e);
		}
	}

	public Member findById(int id) {
		var sql = "select * from member where id = :id";
		return template.queryForObject(sql, Map.of("id", id), rowMapper);
	}

	public int updateNameAndRole(int id, String name, Role role) {
		return template.update("update member set name = :name, role = :role where id = :id",
				Map.of("id", id, "name", name, "role", role.name()));
	}

	public int changePassword(String loginId, String oldPass, String newPass) {

		var secret = template.queryForObject("select password from member where login_id = :loginId",
				Map.of("loginId", loginId), String.class);

		if (!secret.equals(oldPass)) {
			throw new ProjectDbException("Please check your last login id.");
		}

		if (secret.equals(newPass)) {
			throw new ProjectDbException("You can't set the same password. Please change your new password.");
		}

		return template.update("update member set password = :password where login_id = :loginId",
				Map.of("loginId", loginId, "password", newPass));
	}

	public int changeState(String loginId, boolean active) {
		return template.update("update member set active = :active where login_id = :loginId",
				Map.of("loginId", loginId, "active", active));
	}

	public int delete(int id) {
		return template.update("delete from member where id = :id", Map.of("id", id));
	}
	
	public List<MemberVO> search(Role role, String name, Boolean status) {
		
		var sb = new StringBuffer("select * from member where 1 = 1");
		var params = new HashMap<String, Object>();
		
		if(null != role) {
			sb.append(" and role = :role");
			params.put("role", role.name());
		}
		
		if(StringUtils.hasLength(name)) {
			sb.append(" and lower(name) like :name");
			params.put("name", name.toLowerCase().concat("%"));
		}
		
		if(null != status) {
			sb.append(" and active = :status");
			params.put("status", status);
		}

		return template.queryForStream(sb.toString(), params, rowMapper).map(a -> (MemberVO)a).toList();
	}

}
