package com.jdc.assignment.controller;

import org.springframework.beans.BeansException;

public interface BeanFactoryServlet {

	<T> T getBean(String name, Class<T> requiredType) throws BeansException;
	
}
