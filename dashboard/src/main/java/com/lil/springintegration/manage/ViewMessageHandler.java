package com.lil.springintegration.manage;

import com.lil.springintegration.endpoint.TechSupportMessageHandler;
import com.lil.springintegration.util.AppSupportStatus;

class ViewMessageHandler extends TechSupportMessageHandler {

    protected void receiveAndAcknowledge(AppSupportStatus status) {
        DashboardManager.setDashboardStatus("softwareBuild", status.getVersion());
    }
}


