package com.jdc.demo;

import org.springframework.stereotype.Component;

import com.jdc.config.qualifiers.AppServices;
import com.jdc.config.qualifiers.AppServices.Type;

@Component
public class AppClient {

	private AppService service;
	
	public AppClient(@AppServices(Type.TWO) AppService service) {
		super();
		this.service = service;
	}

	public void setService(AppService service) {
		this.service = service;
	}
	
	public AppService getService() {
		return service;
	}
}
