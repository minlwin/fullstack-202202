package com.jdc.balance.model.domain.vo;

import java.util.List;

import com.jdc.balance.model.domain.form.BalanceItemForm;

public class BalanceVo {

	private BalanceSummaryVo header;
	private List<BalanceItemForm> items;

	public BalanceSummaryVo getHeader() {
		return header;
	}

	public void setHeader(BalanceSummaryVo header) {
		this.header = header;
	}

	public List<BalanceItemForm> getItems() {
		return items;
	}

	public void setItems(List<BalanceItemForm> items) {
		this.items = items;
	}

	public int getTotal() {
		return 0;
	}

}