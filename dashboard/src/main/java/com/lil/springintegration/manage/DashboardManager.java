package com.lil.springintegration.manage;

import com.lil.springintegration.service.TechSupportService;
import com.lil.springintegration.service.ViewService;
import com.lil.springintegration.util.AppProperties;
import com.lil.springintegration.util.AppSupportStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.AbstractSubscribableChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import java.util.Date;
import java.util.Properties;

public class DashboardManager {

    static Properties dashboardStatusDao = new Properties();
    static Logger logger = LoggerFactory.getLogger(DashboardManager.class);
    private static AbstractApplicationContext context;

    private static ViewService viewService;
    private static TechSupportService techSupportService;

    public DashboardManager() {
        DashboardManager.context = new ClassPathXmlApplicationContext("/META-INF/spring/application.xml", DashboardManager.class);
        initializeServices();
        initializeDashboard();
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
        // TODO - refactor to use Spring Dependency Injection
        viewService = new ViewService();
        techSupportService = new TechSupportService();
    }

    private void initializeDashboard() {
        DashboardManager.setDashboardStatus("softwareBuild", "undetermined");

        AppProperties props = (AppProperties) DashboardManager.getDashboardContext().getBean("appProperties");

        // Make a domain-specific payload object
        AppSupportStatus status = new AppSupportStatus(props.getRuntimeProperties().getProperty("software.build", "unknown"), new Date());

        // Use MessageBuilder utility class to construct a Message with our domain object as payload
        GenericMessage<?> message = (GenericMessage<?>) MessageBuilder
                .withPayload(status)
                .build();

        /**
         *  Challenge: Change this Subscribable DirectChannel to a PublishSubscribeChannel
         *  Hint: Change the cast in line 69
         */

        // Now, to send our message, we need a channel! (We also need subscribers before this send will be successful.)
        AbstractSubscribableChannel techSupportChannel = (PublishSubscribeChannel) DashboardManager.context.getBean("techSupportChannel");
        techSupportChannel.send(message);
    }

}


