package com.lil.springintegration.endpoint;

import com.lil.springintegration.domain.AppSupportStatus;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.ArrayList;

public class JdbcMessageTransformer {

    public AppSupportStatus transform(ArrayList<LinkedCaseInsensitiveMap<String>> outList) {
        AppSupportStatus x = new AppSupportStatus();
        x.setDeviceOut(outList);
        return x;
    }
}
