package com.lil.springintegration.manage;

import com.lil.springintegration.util.AppProperties;
import com.lil.springintegration.util.AppSupportStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Date;
import java.util.Properties;

public class DashboardManager {

    private static Properties dashboardStatusDao = new Properties();

    static Logger logger = LoggerFactory.getLogger(DashboardManager.class);

    private static AbstractApplicationContext context;

    public DashboardManager() {
        DashboardManager.context = new ClassPathXmlApplicationContext("/META-INF/spring/application.xml", DashboardManager.class);
        initializeTechSupport();
        initializeGridStatus();
        initializeKinetecoNews();
        initializePowerUsage();
    }

    public Properties getDashboardStatus() {
        return DashboardManager.dashboardStatusDao;
    }

    static void setDashboardStatus(String key, String value) {
        String v = (value != null ? value : "");
        DashboardManager.dashboardStatusDao.setProperty(key, v);
    }

    private void initializeTechSupport() {
        AppProperties props = (AppProperties) DashboardManager.context.getBean("appProperties");
        DashboardManager.dashboardStatusDao.setProperty("softwareBuild", props.getRuntimeProperties().getProperty("software.build", "unknown"));

        // Make an AppSupportStatus payload
        // AppSupportStatus status =

        // Build a Message

        // To send our message, we need a channel!

    }

    private void initializeGridStatus() {
    }

    private void initializeKinetecoNews() {
    }

    private void initializePowerUsage()  {
    }



}


