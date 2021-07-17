package com.gap.mcm.rest.request;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.util.Map;

public interface HttpRequestContext {

    HttpEntity<?> getEntity();
    String getUrl();
    HttpMethod getHttpMethod();
    Map<String,Object> getUriParameters();

}
