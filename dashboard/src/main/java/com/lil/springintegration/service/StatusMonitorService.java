package com.lil.springintegration.service;

import com.lil.springintegration.endpoint.TechSupportMessageFilter;
import com.lil.springintegration.endpoint.TechSupportMessageHandler;
import com.lil.springintegration.manage.DashboardManager;
import com.lil.springintegration.util.AppSupportStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.channel.*;
import org.springframework.integration.support.MessageBuilder;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class StatusMonitorService {

    static Logger logger = LoggerFactory.getLogger(DashboardManager.class);
    private Timer timer = new Timer();

    private AppSupportStatus currentLocalStatus;

    // TODO - refactor to use Spring Dependency Injection
    private AbstractSubscribableChannel techSupportChannel;
    private QueueChannel updateNotificationChannel;

    public StatusMonitorService() {
        updateNotificationChannel = (QueueChannel) DashboardManager.getDashboardContext().getBean("updateNotificationQueueChannel");
        techSupportChannel = (PublishSubscribeChannel) DashboardManager.getDashboardContext().getBean("techSupportChannel");
        techSupportChannel.subscribe(new ServiceMessageHandler());
        this.start();
    }

    private void start() {
        /* Represents long-running process thread */
        timer.schedule(new TimerTask() {
            public void run() {
                checkClientStatus();
            }
        }, 10000, 10000);
    }

    private void checkClientStatus() {
        /* Query REST api for client status markers */

        // Simulate an API call and return value
        boolean updateRqd = simulateOccasionalUpdateSignal();

        // Create our payload object from the API return value
        AppSupportStatus thisStatus = new AppSupportStatus(currentLocalStatus.getRunningVersion(), new Date(), updateRqd);
        System.out.println("Our API return indicates that a software update is " + (updateRqd ? "" : "NOT ") + "required.\n");

        // Replace direct send to queue with send to general monitor channel
        techSupportChannel.send(MessageBuilder.withPayload(thisStatus).build());

    }

    public static class ServiceMessageFilter extends TechSupportMessageFilter {
        protected boolean filterMessage(AppSupportStatus status) {
            return status.isUpdateRequired();
        }
    }

    private class ServiceMessageHandler extends TechSupportMessageHandler {
        protected void receive(AppSupportStatus status) {
            setCurrentSupportStatus(status);
        }
    }

    private void setCurrentSupportStatus(AppSupportStatus status) {
        this.currentLocalStatus = status;
    }

    private boolean simulateOccasionalUpdateSignal() {
        Random random = new Random();
        int x = random.nextInt(3);
        return x == 2;
    }

}
