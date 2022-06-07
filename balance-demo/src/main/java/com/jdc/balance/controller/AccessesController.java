package com.jdc.balance.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.balance.controller.utils.Pagination;
import com.jdc.balance.model.domain.entity.UserAccessLog.Type;
import com.jdc.balance.model.service.UserAccessLogService;

@Controller
@RequestMapping("/admin/accesses")
public class AccessesController {
	
	@Autowired
	private UserAccessLogService service;
	
	@GetMapping
	String search(
			@RequestParam(required = false) Type type,
			@RequestParam(required = false) String username,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
			@RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> size,
			ModelMap model
			) {
		
		var result = service.searchAdmin(type, username, date, page, size);
		model.put("list", result.getContent());
		var params = new HashMap<String, String>();
		
		params.put("type", null == type ? "" : type.name());
		params.put("username", StringUtils.hasLength(username) ? username : "");
		params.put("date", null == date ? "" : date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		
		size.ifPresent(a -> params.put("size", a.toString()));
		
		model.put("pager", Pagination.builder("/admin/accesses")
				.page(result)
				.sizes(List.of(5, 10, 25))
				.sizeChageFormId("accessLogSearchForm")
				.params(params).build());
		
		return "access-logs";
	}
	
	@ModelAttribute(name = "types")
	Type[] types() {
		return Type.values();
	}
}
