package com.jdc.demo.scopes;

import org.springframework.beans.factory.config.Scope;

public interface BaseScope extends Scope{

	@Override
	default String getConversationId() {
		return null;
	}
	
	@Override
	default Object resolveContextualObject(String key) {
		return null;
	}
	
	@Override
	default void registerDestructionCallback(String name, Runnable callback) {

	}
}
