package com.jdc.balance;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.jdc.balance.model.domain.entity.User.Role;

@EnableWebSecurity
public class BalanceDemoSecurityConfig {
	
	@Bean
	SecurityFilterChain httpSecurity(HttpSecurity http) throws Exception {
		
		http.formLogin(form -> form.loginPage("/signin").defaultSuccessUrl("/"));
		
		http.logout(logout -> logout.logoutUrl("/signout").logoutSuccessUrl("/"));
		
		http.authorizeHttpRequests(auth -> auth
			.mvcMatchers("/signin", "/signup", "/").permitAll()
			.mvcMatchers("/user/**").hasAnyAuthority(Role.Member.name(), Role.Admin.name())
			.mvcMatchers("/admin/**").hasAuthority(Role.Admin.name())
			.anyRequest().authenticated());
		
		return http.build();
	}

}
