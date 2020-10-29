package com.lil.springintegration.util;

import java.util.Date;

public class AppSupportStatus {

    private String runningVersion;

    private Date snapTime;

    private boolean updateRequired = false;

    private int netSolar = 0;

    private int netWind = 0;

    public String getRunningVersion() {
        return runningVersion;
    }
    public void setRunningVersion(String version) { this.runningVersion = version; }

    public Date getTime() {
        return snapTime;
    }
    public void setTime(Date dttm) { this.snapTime = dttm; }

    public boolean isUpdateRequired() { return updateRequired; }
    public void setIsUpdateRequired(boolean update) { this.updateRequired = update; }

    public int getNetSolar() { return netSolar; }
    public void setNetSolar(int solar) { this.netSolar = solar; }

    public int getNetWind() { return netWind; }
    public void setNetWind(int wind) { this.netWind = wind; }

    public String getCustomerNotification() {
        if (updateRequired) {
            return "A software update is required.";
        }
        return "(none)";
    }

    public String toString() { return runningVersion + "@" + snapTime.toString() + (updateRequired ? "|update" : "|current") + "|" + netSolar + "|" + netWind; }

}
