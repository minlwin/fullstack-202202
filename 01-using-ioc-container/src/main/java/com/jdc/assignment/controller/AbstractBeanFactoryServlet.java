package com.jdc.assignment.controller;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import com.jdc.assignment.listener.SpringContainerManager;

public abstract class AbstractBeanFactoryServlet extends HttpServlet implements BeanFactoryServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		
		var spring = getServletContext().getAttribute(SpringContainerManager.SPRING_CONTEXT);
		
		if(null != spring && spring instanceof BeanFactory factory) {
			return factory.getBean(name, requiredType);
		}
		
		return null;
	}

}
