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
        techSupportChannel = (DirectChannel) DashboardManager.getDashboardContext().getBean("techSupportChannel");
        // techSupportChannel =
        this.start();
    }

    private void start() {
        // subscribe to our message flow tech support channel
        // Spawn long-running process thread
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
