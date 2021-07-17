package com.gap.mcm.rest.client;

import com.gap.mcm.rest.request.HttpRequestContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static java.util.Collections.emptyMap;

@Component
public class HttpRestClientImpl implements HttpRestClient {

    private final RestTemplate restTemplate ;

    public HttpRestClientImpl(RestTemplate restTemplate, @Value("${mcm.restclient.timeout}") int connectionTimeOut) {
        this.restTemplate = getRestTemplate(restTemplate, connectionTimeOut);
    }

    public <T> ResponseEntity<T> call(HttpRequestContext context, Class<T> returnType) {
        return invoke(context.getUrl(), context.getHttpMethod(), context.getEntity(), returnType, context.getUriParameters());
    }

    private <T> ResponseEntity<T> invoke(String uri, HttpMethod httpMethod,
                                         HttpEntity<?> httpEntity, Class<T> type,
                                         Map<String, ?> urlVariables) {
        Map<String, ?> nonNullUriVariables = urlVariables == null ? emptyMap() : urlVariables;

        return restTemplate.exchange(uri, httpMethod, httpEntity, type, nonNullUriVariables);
    }

    private RestTemplate getRestTemplate(RestTemplate restTemplate, int connectionTimeOut) {
        HttpComponentsClientHttpRequestFactory factory  = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(connectionTimeOut);
        factory.setReadTimeout(connectionTimeOut);
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }


}