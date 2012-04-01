package com.jawesa.home.common;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import com.jawesa.model.common.AbstractLongIdEntity;

@SuppressWarnings("serial")
public class EntityLongIdConversationnalHome<E extends AbstractLongIdEntity> extends EntityLongIdHome<E> {
	@Inject
	private Conversation conversation;
	
	public void initConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
	
	public void endConversation() {
		if(!conversation.isTransient()) {
			conversation.end();
		}
	}
	
	@Override
	protected void postCancel() {
		super.postCancel();
		endConversation();
	}
	
	@Override
	protected void postSave() {
		super.postSave();
		endConversation();
	}
}
