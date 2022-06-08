package com.jdc.balance.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping
	String report() {
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
		params.put("dateFrom", null == dateFrom ? "" : dateFrom.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		params.put("dateTo", null == dateTo ? "" : dateTo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		params.put("keyword", null == keyword? "" : keyword);
		
		
		var pagination = Pagination.builder("/user/balance/%s".formatted(type))
				.page(result)
				.params(params)
				.build();
		
		model.put("pagination", pagination);
		
		return "balance-list";
	}
	
	
	@GetMapping("add/{type}")
	public String addNew(ModelMap model, @PathVariable Type type) {
		model.put("title", "%s Management".formatted(type));
		model.put("type", type);
		return "balance-edit";
	}

	@GetMapping("edit/{id:\\d+}")
	public String edit(@PathVariable int id, ModelMap model) {
		model.put("title", "Edit Income");
		model.put("type", "incomes");
		return "balance-edit";
	}

	@PostMapping
	public String save() {
		// TODO implement here
		return "redirect:/user/balance/%d".formatted(1);
	}

	@GetMapping("{id:\\d+}")
	public String findById(@PathVariable int id) {
		System.out.println("Balance Id : %d".formatted(id));
		return "balance-details";
	}

	@GetMapping("delete/{id:\\d+}")
	public String delete(@PathVariable int id) {
		return "redirect:/";
	}

}