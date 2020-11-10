package com.lil.springintegration.service;

import com.lil.springintegration.endpoint.AppStatusMessageHandler;
import com.lil.springintegration.manage.DashboardManager;
import com.lil.springintegration.domain.AppSupportStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.channel.*;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.social.twitter.api.Tweet;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class ViewService {

    static Logger logger = LoggerFactory.getLogger(DashboardManager.class);
    private Timer timer = new Timer();

    // TODO - refactor to use Spring Dependency Injection
    private AbstractSubscribableChannel statusMonitorChannel;
    private QueueChannel updateNotificationChannel;

    public ViewService() {
        updateNotificationChannel = (QueueChannel) DashboardManager.getDashboardContext().getBean("updateNotificationQueueChannel");
        statusMonitorChannel = (PublishSubscribeChannel) DashboardManager.getDashboardContext().getBean("statusMonitorChannel");
        statusMonitorChannel.subscribe(new ViewMessageHandler());
        this.start();
    }

    private void start() {
        /* Represents long-running process thread */
        timer.schedule(new TimerTask() {
            public void run() {
                // Would typically be dependent on some external service resource where throttling was a factor, like email
                checkForNotifications();
            }
        }, 3000, 3000);
    }

    private void checkForNotifications() {
        /* Check queue for notifications */
        GenericMessage<?> message = (GenericMessage<?>) updateNotificationChannel.receive(1000);
        if (message != null) {
            if (message.getPayload() instanceof AppSupportStatus ) {
                AppSupportStatus payload = (AppSupportStatus) message.getPayload();
                DashboardManager.setDashboardStatus("softwareNotification", payload.getCustomerSoftwareNotification());
                DashboardManager.setDashboardStatus("deviceNotification", payload.getCustomerDeviceNotification());
            } else if (message.getPayload() instanceof Tweet) {
                Tweet payload = (Tweet) message.getPayload();
                DashboardManager.setDashboardStatus("latestTweets", payload.getText());
            }
        }
    }

    private static class ViewMessageHandler extends AppStatusMessageHandler {
        protected void receive(AppSupportStatus status) {
            DashboardManager.setDashboardStatus("softwareBuild", status.getRunningVersion());
            DashboardManager.setDashboardStatus("solarUsage", String.valueOf(status.getNetSolar()));
            DashboardManager.setDashboardStatus("windUsage", String.valueOf(status.getNetWind()));
            DashboardManager.setDashboardStatus("creditsToDate", NumberFormat.getCurrencyInstance(Locale.UK).format(CustomerAccountService.getAccountCredit()));
        }
    }

}
