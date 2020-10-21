package com.lil.springintegration.service;

import com.lil.springintegration.endpoint.TechSupportMessageHandler;
import com.lil.springintegration.manage.DashboardManager;
import com.lil.springintegration.util.AppSupportStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TechSupportService {

    static Logger logger = LoggerFactory.getLogger(DashboardManager.class);

    public TechSupportService() {
        // initialize our tech support message flow channel
        this.start();
    }

    private void start() {
        // subscribe to our message flow tech support channel
        // Spawn long-running process thread
    }

    private boolean isVersionCurrent() {
        return true;
    }

    private static class ServiceMessageHandler extends TechSupportMessageHandler {

        protected void receiveAndAcknowledge(AppSupportStatus status) {
            TechSupportService.logger.info("Tech support service received message: " + status.toString());
        }
    }
}
