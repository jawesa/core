package com.jawesa.action.common;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SuppressWarnings("serial")
public class ConversationAction implements Serializable {
	public static final long DEFAULT_TIMEOUT = 300000L;

	@Inject
	private Conversation conversation;

	public void begin() {
		if (isTransient()) {
			conversation.setTimeout(DEFAULT_TIMEOUT);
			conversation.begin();
		}
	}

	public void end() {
		if (!isTransient()) {
			conversation.end();
		}
	}

	public boolean isTransient() {
		return conversation.isTransient();
	}

	public String getDebugDisplay() {
		StringBuffer sb = new StringBuffer();
		sb.append("Conversation #").append(conversation.getId()).append(" (")
				.append(isTransient() ? "transient" : "long-running")
				.append(")");
		return sb.toString();

	}
}
