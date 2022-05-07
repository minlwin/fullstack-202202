package com.jdc.demo.scopes;

import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.stereotype.Component;

@Component
public class AppConfig extends CustomScopeConfigurer{
	
	public AppConfig() {
		setScopes(Map.of("custom", new CustomScope()));
	}
}
