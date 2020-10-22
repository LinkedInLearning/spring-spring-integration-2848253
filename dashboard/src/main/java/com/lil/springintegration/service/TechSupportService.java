package com.lil.springintegration.service;

import com.lil.springintegration.endpoint.TechSupportMessageHandler;
import com.lil.springintegration.manage.DashboardManager;
import com.lil.springintegration.util.AppSupportStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.AbstractSubscribableChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

public class TechSupportService {

    static Logger logger = LoggerFactory.getLogger(DashboardManager.class);

    private AbstractSubscribableChannel techSupportChannel;

    public TechSupportService() {
        techSupportChannel = (PublishSubscribeChannel) DashboardManager.getDashboardContext().getBean("techSupportChannel");
        QueueChannel blah = (QueueChannel) DashboardManager.getDashboardContext().getBean("queueChannel");
        blah.receive();

        this.start();
    }

    private void start() {
        // Subscribe to our tech support channel
        techSupportChannel.subscribe(new ServiceMessageHandler());

        // Represents long-running process thread
        // Poll for changes to version
        GenericMessage<?> message = (GenericMessage<?>) MessageBuilder.withPayload("true").build();
        //orderConfirmationChannel.send(msg);
    }

    private boolean isVersionCurrent() {
        // every five seconds, send new message
        return true;
    }

    private static class ServiceMessageHandler extends TechSupportMessageHandler {

        protected void receiveAndAcknowledge(AppSupportStatus status) {
            TechSupportService.logger.info("Tech support service received new build notification: " + status.toString());
        }
    }
}
