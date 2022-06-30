package com.jdc.demo.entity.listeners;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class TimesListener {

	@PrePersist
	public void beforeCreate(Object object) {
		System.out.println("Before Creation by Listener");
		if(object instanceof TimeEnableEntity entity) {
			var times = entity.getTimes();
			if(null == times) {
				times = new Times();
				entity.setTimes(times);
			}
			times.setCreation(LocalDateTime.now());
		}
	}
	
	@PreUpdate
	public void beforeUpdate(Object object) {
		System.out.println("Before Update by Listener");
		if(object instanceof TimeEnableEntity entity) {
			var times = entity.getTimes();
			if(null == times) {
				times = new Times();
				entity.setTimes(times);
			}
			times.setModification(LocalDateTime.now());
		}
	}
}
