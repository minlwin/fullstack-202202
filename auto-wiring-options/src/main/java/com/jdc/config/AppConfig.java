package com.jdc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jdc.demo.AppClient;
import com.jdc.demo.AppService;
import com.jdc.demo.AppServiceOne;
import com.jdc.demo.AppServiceTwo;

@Configuration
public class AppConfig {

	
	@Bean
	AppClient client(AppService service) {
		var client = new AppClient();
		client.setService(service);
		return client;
	}
	
	@Bean
	AppServiceOne one() {
		return new AppServiceOne();
	}
	
	@Bean
	AppServiceTwo two() {
		return new AppServiceTwo();
	}
}
