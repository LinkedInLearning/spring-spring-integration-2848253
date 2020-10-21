package com.lil.springintegration.service;

import com.lil.springintegration.endpoint.TechSupportMessageHandler;
import com.lil.springintegration.manage.DashboardManager;
import com.lil.springintegration.util.AppSupportStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;


public class TechSupportService {

    static Logger logger = LoggerFactory.getLogger(DashboardManager.class);

    static PublishSubscribeChannel techSupportChannel;

    public TechSupportService() {
        techSupportChannel = (PublishSubscribeChannel) DashboardManager.getDashboardContext().getBean("techSupport");
        this.start();
    }

    private void start() {
        // Spawn long-running process thread
        techSupportChannel.subscribe(new ServiceMessageHandler());
    }

    private boolean isVersionCurrent() {
        return true;
    }

    private static class ServiceMessageHandler extends TechSupportMessageHandler {

        protected void receiveAndAcknowledge(AppSupportStatus status) {
            TechSupportService.logger.info("Tech support service received message: " + status.toString());
        }
    }
}
