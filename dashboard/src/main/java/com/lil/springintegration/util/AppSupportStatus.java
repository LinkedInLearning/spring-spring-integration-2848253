package com.lil.springintegration.util;

import java.util.Date;

public class AppSupportStatus {

    private String runningVersion;

    private Date snapTime;

    private boolean updateRequired;

    public AppSupportStatus(String version, Date dttm) {
        this(version, dttm, false);
    }

    public AppSupportStatus(String version, Date dttm, boolean update) {
        updateRequired = update;
        this.runningVersion = version;
        this.snapTime = dttm;
    }

    public String getRunningVersion() {
        return runningVersion;
    }

    public Date getTime() {
        return snapTime;
    }

    public boolean isUpdateRequired() { return updateRequired; }

    public String getCustomerNotification() {
        if (updateRequired) {
            return "A software update is required.";
        }
        return runningVersion;
    }

    public String toString() { return runningVersion + "@" + snapTime.toString() + (updateRequired ? "update" : "current"); }

}
