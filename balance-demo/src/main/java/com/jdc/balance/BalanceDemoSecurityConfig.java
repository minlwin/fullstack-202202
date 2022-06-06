package com.jdc.balance;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.jdc.balance.model.domain.entity.User.Role;

@EnableWebSecurity
public class BalanceDemoSecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain httpSecurity(HttpSecurity http) throws Exception {
		
		http.formLogin(form -> form.loginPage("/signin").defaultSuccessUrl("/"));
		
		http.logout(logout -> logout.logoutUrl("/signout").logoutSuccessUrl("/"));
		
		http.authorizeHttpRequests(auth -> auth
			.mvcMatchers("/signin", "/signup", "/").permitAll()
			.mvcMatchers("/user/**").hasAnyAuthority(Role.Member.name(), Role.Admin.name())
			.mvcMatchers("/admin/**").hasAuthority(Role.Admin.name())
			.anyRequest().authenticated());
		
		http.exceptionHandling().accessDeniedPage("/denied-page");
		
		return http.build();
	}
	
	@Bean
	AuthenticationEventPublisher authenticationEventPublisher() {
		return new DefaultAuthenticationEventPublisher();
	}
	
	@Bean
	HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

}
