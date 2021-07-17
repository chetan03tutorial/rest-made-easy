package com.gap.mcm.rest.client;

import org.springframework.http.HttpMethod;

public enum HttpRestMethod {
    GET(HttpMethod.GET),POST(HttpMethod.POST),PUT(HttpMethod.PUT),PATCH(HttpMethod.PATCH),DELETE(HttpMethod.DELETE);

    private HttpMethod method;

    HttpRestMethod(HttpMethod method){
        this.method = method;
    }

    public HttpMethod getHttpMethod(){
        return HttpMethod.valueOf(method.name());
    }
}
