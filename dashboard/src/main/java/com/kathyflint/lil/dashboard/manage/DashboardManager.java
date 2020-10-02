package com.kathyflint.lil.dashboard.manage;

import com.kathyflint.lil.dashboard.util.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.*;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DashboardManager {

    private static Properties dashboardStatusDao = new Properties();

    static Logger logger = LoggerFactory.getLogger(DashboardManager.class);

    private static AbstractApplicationContext context;

    public DashboardManager() {
        DashboardManager.context = new ClassPathXmlApplicationContext("/META-INF/spring/application.xml", DashboardManager.class);
        initializeGridStatus();
        initializeKinetecoNews();
        initializePowerUsage();
        initializeTechSupport();
    }

    public Properties getDashboardStatus() {
        return DashboardManager.dashboardStatusDao;
    }

    static void setDashboardStatus(String key, String value) {
        String v = (value != null ? value : "");
        DashboardManager.dashboardStatusDao.setProperty(key, v);
    }

    private void initializeGridStatus() {
    }

    private void initializeKinetecoNews() {
    }

    private void initializePowerUsage()  {
    }

    private void initializeTechSupport() {
        AppProperties props = (AppProperties) DashboardManager.context.getBean("appProperties");
        DashboardManager.dashboardStatusDao.setProperty("softwareBuild", props.getRuntimeProperties().getProperty("software.build", "unknown"));
    }

}


