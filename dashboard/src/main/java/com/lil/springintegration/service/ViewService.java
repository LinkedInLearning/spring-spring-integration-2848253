package com.lil.springintegration.service;

import com.lil.springintegration.endpoint.TechSupportMessageHandler;
import com.lil.springintegration.manage.DashboardManager;
import com.lil.springintegration.util.AppSupportStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.channel.*;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.GenericMessage;

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
    private DirectChannel dataChannel;

    public ViewService() {
        updateNotificationChannel = (QueueChannel) DashboardManager.getDashboardContext().getBean("updateNotificationQueueChannel");
        statusMonitorChannel = (PublishSubscribeChannel) DashboardManager.getDashboardContext().getBean("statusMonitorChannel");
        statusMonitorChannel.subscribe(new ViewMessageHandler());
        dataChannel = (DirectChannel) DashboardManager.getDashboardContext().getBean("dataChannel");
        dataChannel.subscribe(new DeviceMessageHandler());
        this.start();
    }

    private void start() {
        /* Represents long-running process thread */
        timer.schedule(new TimerTask() {
            public void run() {
                checkForNotifications();
            }
        }, 3000, 3000);
    }

    private void checkForNotifications() {
        /* Check queue for notifications that the software needs to be updated */
        GenericMessage<?> message = (GenericMessage<?>) updateNotificationChannel.receive(1000);
        if (message != null) {
            AppSupportStatus payload = (AppSupportStatus) message.getPayload();
            DashboardManager.setDashboardStatus("softwareNotification", payload.getCustomerNotification());
        }
    }

    private static class ViewMessageHandler extends TechSupportMessageHandler {
        protected void receive(AppSupportStatus status) {
            DashboardManager.setDashboardStatus("softwareBuild", status.getRunningVersion());
            DashboardManager.setDashboardStatus("solarUsage", String.valueOf(status.getNetSolar()));
            DashboardManager.setDashboardStatus("windUsage", String.valueOf(status.getNetWind()));
            DashboardManager.setDashboardStatus("creditsToDate", NumberFormat.getCurrencyInstance(Locale.UK).format(CustomerAccountService.getAccountCredit()));
        }
    }

    private static class DeviceMessageHandler implements MessageHandler {
        @Override
        public void handleMessage(Message<?> message) throws MessagingException {
            System.out.println(message.getPayload().toString());
        }
    }
}
