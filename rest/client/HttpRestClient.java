package com.gap.mcm.rest.client;

import com.gap.mcm.rest.request.HttpRequestContext;
import org.springframework.http.ResponseEntity;

public interface HttpRestClient {

    <T> ResponseEntity<T> call(HttpRequestContext context, Class<T> returnType);

}
