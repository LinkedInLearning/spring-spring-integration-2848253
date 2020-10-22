package com.lil.springintegration.service;

import com.lil.springintegration.endpoint.TechSupportMessageHandler;
import com.lil.springintegration.manage.DashboardManager;
import com.lil.springintegration.util.AppSupportStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.AbstractSubscribableChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.util.Timer;
import java.util.TimerTask;

public class TechSupportService {

    static Logger logger = LoggerFactory.getLogger(DashboardManager.class);
    private Timer timer = new Timer();

    private AbstractSubscribableChannel techSupportChannel;

    public TechSupportService() {
         this.start();
    }

    private void start() {
        // Represents long-running process thread
        timer.schedule(new TimerTask() {
            public void run() {
                // Check for more current software version
            }
        }, 0, 3000);
    }

    private void checkVersionCurrency() {
        // Every five seconds, check api for currency a
        // If necessary, push notice to queue
    }

    private static class ServiceMessageHandler extends TechSupportMessageHandler {

        protected void receiveAndAcknowledge(AppSupportStatus status) {
            TechSupportService.logger.info("Tech support service received new build notification: " + status.toString());
        }
    }
}
