package com.lil.springintegration.endpoint;

import com.lil.springintegration.util.AppSupportStatus;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.ArrayList;

public class JdbcMessageTransformer {

    public AppSupportStatus transform(ArrayList<LinkedCaseInsensitiveMap> outList) {
        AppSupportStatus x = new AppSupportStatus();
        x.setDeviceOut(outList);
        return x;
    }
}
