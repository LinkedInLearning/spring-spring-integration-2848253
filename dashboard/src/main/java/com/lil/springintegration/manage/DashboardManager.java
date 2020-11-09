package com.lil.springintegration.manage;

import com.lil.springintegration.service.StatusMonitorService;
import com.lil.springintegration.service.ViewService;
import com.lil.springintegration.domain.AppProperties;
import com.lil.springintegration.domain.AppSupportStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.AbstractSubscribableChannel;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.endpoint.SourcePollingChannelAdapter;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Date;
import java.util.Properties;

public class DashboardManager {

    static Properties dashboardStatusDao = new Properties();
    static Logger logger = LoggerFactory.getLogger(DashboardManager.class);
    private static AbstractApplicationContext context;

    // TODO - refactor to use Spring Dependency Injection
    private static ViewService viewService;
    private static StatusMonitorService statusMonitorService;
    private static SourcePollingChannelAdapter dataPoller, twitterPoller, apiPoller;

    public DashboardManager() {
        DashboardManager.context = new ClassPathXmlApplicationContext("/META-INF/spring/application.xml", DashboardManager.class);
        dataPoller = (SourcePollingChannelAdapter) DashboardManager.getDashboardContext().getBean("gridStatusPoller");
        apiPoller = (SourcePollingChannelAdapter) DashboardManager.getDashboardContext().getBean("apiPoller");
        if (DashboardManager.getDashboardContext().containsBean("twitterPoller")) {
            twitterPoller = (SourcePollingChannelAdapter) DashboardManager.getDashboardContext().getBean("twitterPoller");
        }
        initializeServices();
        initializeView();
    }

    public static ClassPathXmlApplicationContext getDashboardContext() { return (ClassPathXmlApplicationContext) DashboardManager.context; }

    public static void setDashboardStatus(String key, String value) {
        String v = (value != null ? value : "");
        DashboardManager.dashboardStatusDao.setProperty(key, v);
    }

    public static Properties getDashboardStatus() {
        return DashboardManager.dashboardStatusDao;
    }

    private void initializeServices() {
        viewService = new ViewService();
        statusMonitorService = new StatusMonitorService();
        dataPoller.start();
        if (twitterPoller != null) {
            twitterPoller.start();
        }
    }

    public void initCallback() {
        apiPoller.start();
    }

    private void initializeView() {
        DashboardManager.setDashboardStatus("softwareBuild", "...");
        DashboardManager.setDashboardStatus("softwareNotification", "(none)");
        DashboardManager.setDashboardStatus("solarUsage", "...");
        DashboardManager.setDashboardStatus("windUsage", "...");
        DashboardManager.setDashboardStatus("creditsToDate", "...");
        DashboardManager.setDashboardStatus("devicesNotification", "");
        if (twitterPoller != null) {
            DashboardManager.setDashboardStatus("latestTweets", "");
        } else {
            DashboardManager.setDashboardStatus("latestTweets", "README: To activate live Twitter feed, see instructions at application.xml, line 41.");
        }

        AppProperties props = (AppProperties) DashboardManager.getDashboardContext().getBean("appProperties");
        String v = props.getRuntimeProperties().getProperty("software.build", "unknown");
        Date d = new Date();

        // Make a status domain object
        AppSupportStatus status = new AppSupportStatus();
        status.setRunningVersion(v);
        status.setTime(d);

        // Use MessageBuilder utility class to construct a Message with our domain object as payload
        GenericMessage<?> message = (GenericMessage<?>) MessageBuilder
                .withPayload(status)
                .build();

        // Now, to send our message, we need a channel! (We also need subscribers before this send will be successful.)
        AbstractSubscribableChannel statusMonitorChannel = (PublishSubscribeChannel) DashboardManager.getDashboardContext().getBean("statusMonitorChannel");
        statusMonitorChannel.send(message);
    }

}


