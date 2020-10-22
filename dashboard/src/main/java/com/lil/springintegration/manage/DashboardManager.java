package com.lil.springintegration.manage;

import com.lil.springintegration.endpoint.TechSupportMessageHandler;
import com.lil.springintegration.service.TechSupportService;
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

    public DashboardManager() {
        DashboardManager.context = new ClassPathXmlApplicationContext("/META-INF/spring/application.xml", DashboardManager.class);
        initializeView();
        initializeTechSupport();
        initializeGridStatus();
        initializeKinetecoNews();
        initializePowerUsage();
    }

    public static ClassPathXmlApplicationContext getDashboardContext() { return (ClassPathXmlApplicationContext) DashboardManager.context; }

    static void setDashboardStatus(String key, String value) {
        String v = (value != null ? value : "");
        DashboardManager.dashboardStatusDao.setProperty(key, v);
    }

    public static Properties getDashboardStatus() {
        return DashboardManager.dashboardStatusDao;
    }

    private void initializeView() {
        DashboardManager.setDashboardStatus("softwareBuild", "undetermined");
        PublishSubscribeChannel techSupportChannel = (PublishSubscribeChannel) DashboardManager.context.getBean("techSupport");
        techSupportChannel.subscribe(new ViewMessageHandler());
    }

    private void initializeTechSupport() {

        TechSupportService support = new TechSupportService();

        AppProperties props = (AppProperties) DashboardManager.context.getBean("appProperties");

        TechSupportService service = new TechSupportService();

        // Make an domain-specific payload object
        AppSupportStatus status = new AppSupportStatus(props.getRuntimeProperties().getProperty("software.build", "unknown"), new Date());

        // Use MessageBuilder utility class to construct a Message with our domain object as payload
        GenericMessage<?> message = (GenericMessage<?>) MessageBuilder
                .withPayload(status)
                .build();

        // Now, to send our message, we need a channel!
        AbstractSubscribableChannel techSupportChannel = (PublishSubscribeChannel) DashboardManager.context.getBean("techSupport");
        techSupportChannel.send(message);
    }

    private void initializeGridStatus() {
    }

    private void initializeKinetecoNews() {
    }

    private void initializePowerUsage()  {
    }

    private static class ViewMessageHandler extends TechSupportMessageHandler {

        protected void receiveAndAcknowledge(AppSupportStatus status) {
            DashboardManager.setDashboardStatus("softwareBuild", status.getVersion());
        }
    }

}


