package com.lil.springintegration.endpoint;

import com.lil.springintegration.domain.AppSupportStatus;
import org.springframework.integration.MessageRejectedException;
import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;

public abstract class AppStatusMessageFilter implements MessageSelector {

    @Override
    public boolean accept(Message<?> message) throws MessagingException {
        Object payload = message.getPayload();
        if (payload instanceof AppSupportStatus) {
            return filterMessage((AppSupportStatus) payload);
        } else {
            throw new MessageRejectedException(message, "Unknown data type has been received: " + payload.getClass());
        }
    }

    protected abstract boolean filterMessage(AppSupportStatus status);
}
