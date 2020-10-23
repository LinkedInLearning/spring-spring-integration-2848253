package com.lil.springintegration.service;

import com.lil.springintegration.endpoint.TechSupportMessageHandler;
import com.lil.springintegration.manage.DashboardManager;
import com.lil.springintegration.util.AppSupportStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.channel.*;
import org.springframework.messaging.support.GenericMessage;

import java.util.Timer;
import java.util.TimerTask;

public class ViewService {

    static Logger logger = LoggerFactory.getLogger(DashboardManager.class);
    private Timer timer = new Timer();

    // TODO - refactor to use Spring Dependency Injection
    private AbstractSubscribableChannel techSupportChannel;
    private QueueChannel updateNotificationChannel;

    public ViewService() {

        // Initialize our updateNotificationChannel
        updateNotificationChannel = (QueueChannel) DashboardManager.getDashboardContext().getBean("updateNotificationQueueChannel");

        /**
         *  Challenge: Change this Subscribable DirectChannel to a PublishSubscribeChannel
         *  Hint: Change the cast in line 36
         */

        techSupportChannel = (PublishSubscribeChannel) DashboardManager.getDashboardContext().getBean("techSupportChannel");
        techSupportChannel.subscribe(new ViewMessageHandler());
        this.start();
    }

    private void start() {
        // Represents long-running process thread
        timer.schedule(new TimerTask() {
            public void run() {
                checkForNotifications();
            }
        }, 3000, 3000);
    }

    private void checkForNotifications() {
        // Check queue for notifications that the software needs to be updated
        GenericMessage<?> message = (GenericMessage<?>) updateNotificationChannel.receive(1000);
        if (message != null) {
            DashboardManager.setDashboardStatus("softwareBuild", message.getPayload().toString());
        }
    }

    private static class ViewMessageHandler extends TechSupportMessageHandler {
        protected void receiveAndAcknowledge(AppSupportStatus status) {
            DashboardManager.setDashboardStatus("softwareBuild", status.getVersion());
        }
    }
}
