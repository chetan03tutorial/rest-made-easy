package com.gap.mcm.rest.request;

import com.gap.mcm.rest.client.HttpRestMethod;
import java.util.Map;

public interface HttpRequestBuilder extends RequestBuilder {

    HttpRequestBuilder body(Object body) ;

    HttpRequestBuilder url(String url);

    HttpRequestBuilder httpMethod(HttpRestMethod method);

    HttpRequestBuilder body(String body);

    HttpRequestBuilder headers(Map<String,String> hdr);

    HttpRequestBuilder addHeader(String key, String value);

    HttpRequestBuilder uriParam(Map<String,Object> uParams);

    HttpRequestBuilder addUriParam(String key, Object value);
}
