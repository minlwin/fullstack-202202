package com.jdc.demo.entity.listeners;

import java.io.Serializable;

public interface TimeEnableEntity extends Serializable{

	void setTimes(Times times);
	Times getTimes();
}
