package com.kathyflint.lil.dashboard.util;

import java.util.Properties;

public class AppProperties {

    private Properties runtimeProperties;

    public void setRuntimeProperties(Properties props) {
        this.runtimeProperties = props;
    }

    public Properties getRuntimeProperties() {
        return runtimeProperties;
    }
}
