package com.jdc.balance.model.domain.form;

import java.util.List;

public class BalanceEditForm {

	private BalanceSummaryForm header;
	private List<BalanceItemForm> items;

	public BalanceSummaryForm getHeader() {
		return header;
	}

	public void setHeader(BalanceSummaryForm header) {
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