package com.jdc.balance.controller.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

public class Pagination {

	private int current;
	private int total;
	private boolean first;
	private boolean last;
	private String url;
	
	private Map<String, String> params;

	private List<Integer> pages;
	private List<Integer> sizes;
	private String sizeChageFormId;
	
	public boolean isShow() {
		return pages.size() > 1;
	}

	public static Builder builder(String url) {
		return new Builder(url);
	}

	public static class Builder {

		private int current;
		private int total;
		private boolean first;
		private boolean last;
		private String url;
		private List<Integer> sizes;
		private Map<String, String> params;
		private String sizeChageFormId;

		public Builder(String url) {
			this.url = url;
		}

		public <T> Builder page(Page<T> page) {
			this.current = page.getNumber();
			this.total = page.getTotalPages();
			this.first = page.isFirst();
			this.last = page.isLast();
			return this;
		}

		public Builder current(int current) {
			this.current = current;
			return this;
		}

		public Builder total(int total) {
			this.total = total;
			return this;
		}

		public Builder first(boolean first) {
			this.first = first;
			return this;
		}

		public Builder last(boolean last) {
			this.last = last;
			return this;
		}

		public Builder params(Map<String, String> params) {
			this.params = params;
			return this;
		}

		public Builder sizes(List<Integer> sizes) {
			this.sizes = sizes;
			return this;
		}

		public Builder sizeChageFormId(String sizeChageFormId) {
			this.sizeChageFormId = sizeChageFormId;
			return this;
		}

		public Pagination build() {
			return new Pagination(current, total, first, last, url, params, sizes, sizeChageFormId);
		}
	}

	private Pagination(int current, int total, boolean first, boolean last, String url, Map<String, String> params, List<Integer> sizes, String sizeChangeFormId)  {
		super();
		this.total = total;
		this.current = current;
		this.first = first;
		this.last = last;
		this.params = null == params ? new HashMap<String, String>() : params;
		this.sizes = null == sizes ? new ArrayList<>() : sizes;
		this.sizeChageFormId = sizeChangeFormId;

		pages = new ArrayList<>();
		pages.add(current);

		while (pages.size() < 3 && pages.get(0) > 0) {
			pages.add(0, pages.get(0) - 1);
		}

		while (pages.size() < 5 && pages.get(pages.size() - 1) < total - 1) {
			pages.add(pages.get(pages.size() - 1) + 1);
		}

		while (pages.size() < 5 && pages.get(0) > 0) {
			pages.add(0, pages.get(0) - 1);
		}
		
	}
	
	public String getSizeChageFormId() {
		return sizeChageFormId;
	}
	
	public List<Integer> getSizes() {
		return sizes;
	}

	public String getParams() {
		return params.entrySet().stream().map(a -> "%s=%s".formatted(a.getKey(), a.getValue())).reduce("",
				(a, b) -> "%s&%s".formatted(a, b));
	}

	public int getCurrent() {
		return current;
	}

	public int getTotal() {
		return total;
	}

	public boolean isFirst() {
		return first;
	}

	public boolean isLast() {
		return last;
	}

	public List<Integer> getPages() {
		return pages;
	}

	public String getUrl() {
		return url;
	}

}
