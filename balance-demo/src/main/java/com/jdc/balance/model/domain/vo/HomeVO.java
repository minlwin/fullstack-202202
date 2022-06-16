package com.jdc.balance.model.domain.vo;

public class HomeVO {

	private Data income;
	private Data expense;
	
	public HomeVO() {
		income = new Data();
		expense = new Data();
	}

	public Data getIncome() {
		return income;
	}

	public void setIncome(Data income) {
		this.income = income;
	}

	public Data getExpense() {
		return expense;
	}

	public void setExpense(Data expense) {
		this.expense = expense;
	}

	public static class Data {
		private int monthly;
		private int total;

		public int getMonthly() {
			return monthly;
		}

		public void setMonthly(int monthly) {
			this.monthly = monthly;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

	}

}
