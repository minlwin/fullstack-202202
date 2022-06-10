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
import org.springframework.util.StringUtils;

import com.jdc.balance.model.domain.entity.Balance.Type;
import com.jdc.balance.model.domain.entity.BalanceItem;
import com.jdc.balance.model.domain.form.BalanceEditForm;
import com.jdc.balance.model.repo.BalanceItemRepo;

@Service
public class BalanceService {

	@Autowired
	private BalanceItemRepo itemRepo;

	@PreAuthorize("authenticated()")
	public Page<BalanceItem> searchItems(Type type, LocalDate dateFrom, LocalDate dateTo, String keyword,
			Optional<Integer> page, Optional<Integer> size) {

		var pageInfo = PageRequest.of(page.orElse(0), size.orElse(10));

		// Login User Specification
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		Specification<BalanceItem> spec = (root, query, builder) -> builder.equal(root.get("balance").get("user").get("loginId"),
				username);

		// Type Specification
		spec = spec.and((root, query, builder) -> builder.equal(root.get("balance").get("type"), type));

		// date from specification
		if (null != dateFrom) {
			spec = spec.and((root, query, builder) -> builder.greaterThanOrEqualTo(root.get("balance").get("date"), dateFrom));
		}

		// date to specification
		if (null != dateTo) {
			spec = spec.and((root, query, builder) -> builder.lessThanOrEqualTo(root.get("balance").get("date"), dateTo));
		}

		// keyword specification
		if (StringUtils.hasLength(keyword)) {
			Specification<BalanceItem> category = (root, query, builder) -> builder
					.like(builder.lower(root.get("balance").get("category")), "%%%s%%".formatted(keyword.toLowerCase()));
			Specification<BalanceItem> item = (root, query, builder) -> builder.like(builder.lower(root.get("item")),
					"%%%s%%".formatted(keyword.toLowerCase()));
			
			spec = spec.and(item.or(category));
		}

		return itemRepo.findAll(spec, pageInfo);
	}

	public BalanceEditForm featchForm(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object save(BalanceEditForm form) {
		// TODO Auto-generated method stub
		return null;
	}

}
