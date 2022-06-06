package com.jdc.balance.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.model.domain.entity.UserAccessLog;
import com.jdc.balance.model.domain.entity.UserAccessLog.Type;
import com.jdc.balance.model.repo.UserAccessLogRepo;

@Component
public class BalanceAppAccessListener {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserAccessLogRepo accessLogRepo;
	
	@EventListener
	@Transactional
	void onSuccess(AuthenticationSuccessEvent event) {
		var time = LocalDateTime.ofInstant(
				new Date(event.getTimestamp()).toInstant(), ZoneId.systemDefault());
		var username = event.getAuthentication().getName();
		log.info("{} is sign in at {}.", username, time);
		
		accessLogRepo.save(new UserAccessLog(username, Type.Singin, time));
	}
	
	@EventListener
	@Transactional
	void onFailure(AbstractAuthenticationFailureEvent event) {
		var time = LocalDateTime.ofInstant(
				new Date(event.getTimestamp()).toInstant(), ZoneId.systemDefault());
		var username = event.getAuthentication().getName();
		log.info("{} is fail to sign in at {} because of {}.", 
				username, time, event.getException().getMessage());
		accessLogRepo.save(new UserAccessLog(username, Type.Error, time, event.getException().getMessage()));
	}
	
	@EventListener
	@Transactional
	void onSessionDestroied(HttpSessionDestroyedEvent event) {
		
		event.getSecurityContexts().stream().findAny()
			.ifPresent(auth -> {
				var time = LocalDateTime.ofInstant(
						new Date(event.getTimestamp()).toInstant(), ZoneId.systemDefault());
				var username = auth.getAuthentication().getName();
				log.info("{} is sign out at {}.", username, time);
				accessLogRepo.save(new UserAccessLog(username, Type.Singout, time));
			});
	}
}
