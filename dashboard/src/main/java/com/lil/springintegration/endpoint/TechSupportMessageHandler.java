package com.lil.springintegration.endpoint;

import com.lil.springintegration.util.AppSupportStatus;
import org.springframework.integration.MessageRejectedException;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

public abstract class TechSupportMessageHandler implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        Object payload = message.getPayload();
        if (payload instanceof AppSupportStatus) {
            receiveAndAcknowledge((AppSupportStatus) payload);
        } else {
            throw new MessageRejectedException(message, "Unknown data type has been received.");
        }
    }

    protected abstract void receiveAndAcknowledge(AppSupportStatus status);

}


