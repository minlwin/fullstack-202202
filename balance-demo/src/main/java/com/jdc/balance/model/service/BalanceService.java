package com.jdc.balance.model.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.balance.model.domain.entity.Balance;
import com.jdc.balance.model.domain.entity.Balance.Type;
import com.jdc.balance.model.domain.entity.BalanceItem;
import com.jdc.balance.model.domain.form.BalanceEditForm;
import com.jdc.balance.model.domain.vo.BalanceReportVo;
import com.jdc.balance.model.repo.BalanceItemRepo;
import com.jdc.balance.model.repo.BalanceRepo;
import com.jdc.balance.model.repo.UserRepo;

@Service
public class BalanceService {

	@Autowired
	private BalanceItemRepo itemRepo;

	@Autowired
	private BalanceRepo repo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private Integer defaultPageSize;

	@PreAuthorize("authenticated()")
	public Page<BalanceItem> searchItems(Type type, LocalDate dateFrom, LocalDate dateTo, String keyword,
			Optional<Integer> page, Optional<Integer> size) {

		var pageInfo = PageRequest.of(page.orElse(0), size.orElse(defaultPageSize));

		// Login User Specification
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		Specification<BalanceItem> spec = (root, query, builder) -> builder
				.equal(root.get("balance").get("user").get("loginId"), username);

		// Type Specification
		spec = spec.and((root, query, builder) -> builder.equal(root.get("balance").get("type"), type));

		// date from specification
		if (null != dateFrom) {
			spec = spec.and(
					(root, query, builder) -> builder.greaterThanOrEqualTo(root.get("balance").get("date"), dateFrom));
		}

		// date to specification
		if (null != dateTo) {
			spec = spec
					.and((root, query, builder) -> builder.lessThanOrEqualTo(root.get("balance").get("date"), dateTo));
		}

		// keyword specification
		if (StringUtils.hasLength(keyword)) {
			Specification<BalanceItem> category = (root, query, builder) -> builder.like(
					builder.lower(root.get("balance").get("category")), "%%%s%%".formatted(keyword.toLowerCase()));
			Specification<BalanceItem> item = (root, query, builder) -> builder.like(builder.lower(root.get("item")),
					"%%%s%%".formatted(keyword.toLowerCase()));

			spec = spec.and(item.or(category));
		}

		return itemRepo.findAll(spec, pageInfo);
	}

	public BalanceEditForm findById(Integer id) {
		return repo.findById(id).map(BalanceEditForm::new).orElseThrow();
	}

	@Transactional
	public int save(BalanceEditForm form) {

		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		var user = userRepo.findOneByLoginId(username).orElseThrow();

		var balance = form.getHeader().getId() == 0 ? new Balance()
				: repo.findById(form.getHeader().getId()).orElseThrow();
		balance.setUser(user);
		balance.setCategory(form.getHeader().getCategory());
		balance.setDate(form.getHeader().getDate());
		balance.setType(form.getHeader().getType());

		balance = repo.save(balance);

		for (var formItem : form.getItems()) {

			var item = formItem.getId() == 0 ? new BalanceItem() : itemRepo.findById(formItem.getId()).orElseThrow();

			if (formItem.isDeleted()) {
				itemRepo.delete(item);
				continue;
			}

			item.setItem(formItem.getItem());
			item.setUnitPrice(formItem.getUnitPrice());
			item.setQuantity(formItem.getQuantity());
			item.setBalance(balance);

			itemRepo.save(item);
		}

		return balance.getId();
	}

	@Transactional
	public void deleteById(int id) {
		repo.deleteById(id);
	}

	@PreAuthorize("authenticated()")
	public Page<BalanceReportVo> searchReport(Type type, LocalDate dateFrom, LocalDate dateTo, Optional<Integer> page,
			Optional<Integer> size) {

		Specification<Balance> spec = (root, query, builder) -> builder.equal(root.get("user").get("loginId"),
				SecurityContextHolder.getContext().getAuthentication().getName());

		if (null != type) {
			spec = spec.and((root, query, builder) -> builder.equal(root.get("type"), type));
		}

		if (null != dateFrom) {
			spec = spec.and((root, query, builder) -> builder.greaterThanOrEqualTo(root.get("date"), dateFrom));
		}

		if (null != dateTo) {
			spec = spec.and((root, query, builder) -> builder.lessThanOrEqualTo(root.get("date"), dateTo));
		}

		var pageInfo = PageRequest.of(page.orElse(0), size.orElse(defaultPageSize));

		var result = repo.findAll(spec, pageInfo).map(BalanceReportVo::new);
		

		// Net Balance Calculation
		if(!result.getContent().isEmpty()) {
			var firstId = result.getContent().get(0).getId();
			
			var lastIncomes = itemRepo.getLastBalance(firstId, Type.Income).map(a -> a.intValue()).orElse(0);
			var lastExpenses = itemRepo.getLastBalance(firstId, Type.Expense).map(a -> a.intValue()).orElse(0);
			var lastBalance = lastIncomes - lastExpenses;
			
			for(var vo : result.getContent()) {
				if(vo.getType() == Type.Income) {
					lastBalance += vo.getAmount();
				} else {
					lastBalance -= vo.getAmount();
				}
				
				vo.setBalance(lastBalance);
			}
		}

		return result;
	}

}
