package com.lil.springintegration.service;

import com.lil.springintegration.endpoint.TechSupportMessageFilter;
import com.lil.springintegration.endpoint.TechSupportMessageHandler;
import com.lil.springintegration.manage.DashboardManager;
import com.lil.springintegration.util.AppSupportStatus;
import org.json.JSONException;
import org.json.JSONObject;
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
    private AbstractSubscribableChannel statusMonitorChannel;
    private DirectChannel apiInputChannel;

    public StatusMonitorService() {
        apiInputChannel = (DirectChannel) DashboardManager.getDashboardContext().getBean("apiInputChannel");
        statusMonitorChannel = (PublishSubscribeChannel) DashboardManager.getDashboardContext().getBean("statusMonitorChannel");
        statusMonitorChannel.subscribe(new ServiceMessageHandler());
        this.start();
    }

    private void start() {
        /* Represents long-running process thread */
        // TODO - refactor to use Spring Integration poller
        timer.schedule(new TimerTask() {
            public void run() {
                checkClientStatus();
            }
        }, 10000, 10000);
    }

    private void checkClientStatus() {
        /* Query REST api for client status markers */

        String rawJson = simulateRestApiCall();
        System.out.println(rawJson);

        // Send this message to the status monitor channel instead of directly to the queue
        apiInputChannel.send(MessageBuilder
                .withPayload(rawJson)
                .build());
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

    private String simulateRestApiCall() {
        Random random = new Random();
        JSONObject json = new JSONObject();
        try {
            json.put("runningVersion", this.currentLocalStatus.getRunningVersion());
            json.put("snapTime", new Date().toString());
            json.put("updateRequired", random.nextBoolean());
            json.put("netSolar", random.nextInt(40));
            json.put("netWind", random.nextInt(40));
        } catch (JSONException e) {
            logger.info(e.toString());
        }
        return json.toString();
    }

}
