package com.jdc.balance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jdc.balance.model.BalanceAppException;
import com.jdc.balance.model.domain.entity.Balance.Type;
import com.jdc.balance.model.domain.form.BalanceEditForm;
import com.jdc.balance.model.service.BalanceService;

@Controller
@RequestMapping("user/balance-edit")
@SessionAttributes("balanceEditForm")
public class BalanceEditController {
	
	@Autowired
	private BalanceService service;

	@GetMapping
	public String edit() {
		return "balance-edit";
	}

	@PostMapping
	public String save() {
		// TODO implement here
		return "redirect:/user/balance/%d".formatted(1);
	}
	
	@ModelAttribute("balanceEditForm")
	public BalanceEditForm form(@RequestParam(required = false) Integer id, @RequestParam(required = false) Type type) {
		
		if(null != id) {
			return service.featchForm(id);
		}
		
		if(null == type) {
			throw new BalanceAppException("Please set type for balance.");
		}
		
		return new BalanceEditForm().type(type);
	}

}
