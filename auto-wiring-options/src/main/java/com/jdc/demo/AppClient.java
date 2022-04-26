package com.jdc.demo;

public class AppClient {

	private AppService service;
	
	public void setService(AppService service) {
		this.service = service;
	}
	
	public AppService getService() {
		return service;
	}
}
