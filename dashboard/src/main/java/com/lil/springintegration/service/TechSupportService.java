package com.lil.springintegration.service;

import com.lil.springintegration.endpoint.TechSupportMessageHandler;
import com.lil.springintegration.manage.DashboardManager;
import com.lil.springintegration.util.AppSupportStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.channel.AbstractSubscribableChannel;


public class TechSupportService {

    static Logger logger = LoggerFactory.getLogger(DashboardManager.class);

    private AbstractSubscribableChannel techSupportChannel;

    public TechSupportService() {
        //techSupportChannel = (PublishSubscribeChannel) DashboardManager.getDashboardContext().getBean("techSupportChannel");
        this.start();
    }

    private void start() {
        // Represents long-running process thread

        // Subscribe to our tech support channel
        //techSupportChannel.subscribe(new ServiceMessageHandler());

    }

    private boolean isVersionCurrent() {
        return true;
    }

    private static class ServiceMessageHandler extends TechSupportMessageHandler {

        protected void receiveAndAcknowledge(AppSupportStatus status) {
            TechSupportService.logger.info("Tech support service received new build notification: " + status.toString());
        }
    }
}
