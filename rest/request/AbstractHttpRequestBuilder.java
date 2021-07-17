package com.gap.mcm.rest.request;

import com.gap.mcm.rest.client.HttpRestMethod;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

abstract public class AbstractHttpRequestBuilder implements HttpRequestBuilder {
    private String url;
    private HttpRestMethod method;
    private Object requestBody;
    private Map<String,String> headers;
    private Map<String,Object> uriParams;

    abstract public String convert(Object body);

    public HttpRequestBuilder body(Object body) {
        this.requestBody = body;
        return this;
    }

    public HttpRequestBuilder url(String url){
        this.url = url;
        return this;
    }

    public HttpRequestBuilder httpMethod(HttpRestMethod method){
        this.method = method;
        return this;
    }

    public HttpRequestBuilder body(String body){
        this.requestBody = body;
        return this;
    }

    public HttpRequestBuilder headers(Map<String,String> hdr){

        headers = Optional.ofNullable(headers)
                .orElseGet(HashMap::new);

        Optional.ofNullable(hdr)
                .orElseGet(HashMap::new)
                .forEach(headers::put);
        return this;
    }

    public HttpRequestBuilder addHeader(String key, String value){
        headers = Optional.ofNullable(headers)
                .orElseGet(HashMap::new);
        headers.put(key,value);
        return this;
    }

    public HttpRequestBuilder uriParam(Map<String,Object> uParams){

        uriParams = Optional.ofNullable(uParams)
                .orElseGet(HashMap::new);

        Optional.ofNullable(uParams)
                .orElseGet(HashMap::new)
                .forEach(uriParams::put);
        return this;
    }


    public HttpRequestBuilder addUriParam(String key, Object value){
        uriParams = Optional.ofNullable(uriParams).orElseGet(HashMap::new);
        uriParams.put(key,value);
        return this;
    }


    public HttpRequestContext build(){
        return new RestRequestContext();
    }

    private class RestRequestContext implements HttpRequestContext {

        @Override
        public HttpEntity<?> getEntity() {
            HttpHeaders httpHeaders = new HttpHeaders();
            Optional.ofNullable(headers).orElseGet(HashMap::new)
                    .forEach(httpHeaders::add);
            httpHeaders.add("Content-Type", APPLICATION_JSON_VALUE);
            return new HttpEntity<>(requestBody,httpHeaders);
        }

        @Override
        public String getUrl() {
            return url;
        }

        @Override
        public HttpMethod getHttpMethod() {
            return method.getHttpMethod();
        }

        @Override
        public Map<String, Object> getUriParameters() {
            return uriParams;
        }
    }
}
