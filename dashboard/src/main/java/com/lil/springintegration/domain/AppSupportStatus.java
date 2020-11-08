package com.lil.springintegration.domain;

import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.ArrayList;
import java.util.Date;

public class AppSupportStatus {

    private String runningVersion;
    private Date snapTime = new Date();
    private boolean updateRequired = false;
    private int netSolar = 0;
    private int netWind = 0;
    private ArrayList<LinkedCaseInsensitiveMap<String>> deviceOut = new ArrayList<>();

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

    public ArrayList<LinkedCaseInsensitiveMap<String>> getDeviceOut() { return this.deviceOut; }
    public void setDeviceOut(ArrayList<LinkedCaseInsensitiveMap<String>> out) { this.deviceOut = out; }

    public double getAccountCreditEarned() {
        return (netSolar + netWind) * .0001;
    }

    public boolean isDeviceOut() { return deviceOut.iterator().hasNext(); }

    public String getCustomerSoftwareNotification() {
        if (updateRequired) {
            return "A software update is required.";
        }
        return "(none)";
    }

    public String getCustomerDeviceNotification() {
        if (!deviceOut.isEmpty()) {
            return "Your power grid has one or more devices offline.";
        }
        return null;
    }

    public String toString() { return runningVersion + "@" + snapTime.toString() + (updateRequired ? "|update" : "|current") + "|" + netSolar + "|" + netWind + "|" + deviceOut.toString(); }

}
