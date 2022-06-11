package com.jdc.balance.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.balance.controller.utils.Pagination;
import com.jdc.balance.model.domain.entity.Balance.Type;
import com.jdc.balance.model.service.BalanceService;

@Controller
@RequestMapping("user/balance")
public class BalanceController {
	
	@Autowired
	private BalanceService service;
	
	@Autowired
	DateTimeFormatter dateFormatter;
	
	@GetMapping
	String report(
			ModelMap model,
			@RequestParam(required = false) Type type,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo,
			@RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> size) {
		
		var result = service.searchReport(type, dateFrom, dateTo, page, size);
		var pagination = Pagination.builder("/user/balance")
				.params(Map.of(
					"type", null == type ? "" : type.name(),
					"dateFrom", null == dateFrom ? "" : dateFrom.format(dateFormatter),
					"dateTo", null == dateTo ? "" : dateTo.format(dateFormatter)
				))
				.page(result)
				.build();
		
		model.put("pagination", pagination);
		model.put("list", result.getContent());
		
		return "balance-report";
	}
	
	@GetMapping("{type}")
	String searchBalanceItems(
			ModelMap model, 
			@PathVariable Type type, 
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo, 
			@RequestParam(required = false) String keyword,
			@RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> size
			) {
		model.put("title", "%s Management".formatted(type));
		model.put("type", type);
		
		var result = service.searchItems(type, dateFrom, dateTo, keyword, page, size);
		model.put("list", result.getContent());
		
		var params = new HashMap<String, String>();
		params.put("type", type.name());
		params.put("dateFrom", null == dateFrom ? "" : dateFrom.format(dateFormatter));
		params.put("dateTo", null == dateTo ? "" : dateTo.format(dateFormatter));
		params.put("keyword", null == keyword? "" : keyword);
		
		
		var pagination = Pagination.builder("/user/balance/%s".formatted(type))
				.page(result)
				.params(params)
				.build();
		
		model.put("pagination", pagination);
		
		return "balance-list";
	}
	
	
	@GetMapping("details/{id:\\d+}")
	public String findById(@PathVariable int id, ModelMap model) {
		model.put("vo", service.findById(id));
		return "balance-details";
	}

	@GetMapping("delete/{id:\\d+}")
	public String delete(@PathVariable int id) {
		service.deleteById(id);
		return "redirect:/";
	}
	
	@ModelAttribute("balanceTypes")
	Type [] types() {
		return Type.values();
	}

}