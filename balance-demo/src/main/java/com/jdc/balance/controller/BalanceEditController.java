package com.jdc.balance.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jdc.balance.model.BalanceAppException;
import com.jdc.balance.model.domain.entity.Balance.Type;
import com.jdc.balance.model.domain.form.BalanceEditForm;
import com.jdc.balance.model.domain.form.BalanceItemForm;
import com.jdc.balance.model.domain.form.BalanceSummaryForm;
import com.jdc.balance.model.service.BalanceService;

@Controller
@RequestMapping("user/balance-edit")
@SessionAttributes("balanceEditForm")
public class BalanceEditController {
	
	@Autowired
	private BalanceService service;

	@GetMapping
	public String edit(@ModelAttribute("balanceEditForm") BalanceEditForm form, 
			@RequestParam(required = false) Integer id, 
			@RequestParam(required = false) Type type) {
		
		if(null != id && form.getHeader().getId() != id) {
			var result = service.featchForm(id);
			form.setHeader(result.getHeader());
			form.setItems(result.getItems());
		}
		
		if(null != type && !form.getHeader().getType().equals(type)) {
			form.setHeader(new BalanceSummaryForm());
			form.getHeader().setType(type);
			form.getItems().clear();
		}
		
		return "balance-edit";
	}

	@PostMapping("item")
	public String addItem(
			@ModelAttribute("balanceEditForm") BalanceEditForm form, 
			@ModelAttribute("itemForm") @Valid BalanceItemForm itemForm, 
			BindingResult result) {
		
		if(result.hasErrors()) {
			return "balance-edit";
		}
		
		form.getItems().add(itemForm);
		
		var queryParam = form.getHeader().getId() == 0 ? "type=%s".formatted(form.getHeader().getType()) : "id=%s".formatted(form.getHeader().getId());
		
		return "redirect:/user/balance-edit?%s".formatted(queryParam);
	}
	
	@GetMapping("confirm")
	public String confirm() {
		return "balance-edit-confirm";
	}

	@PostMapping
	public String save() {
		// TODO implement here
		return "redirect:/user/balance/%d".formatted(1);
	}
	
	@ModelAttribute("itemForm")
	BalanceItemForm itemForm() {
		return new BalanceItemForm();
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
