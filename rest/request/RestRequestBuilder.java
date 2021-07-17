package com.gap.mcm.rest.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gap.mcm.exception.JsonParsingException;
import com.gap.mcm.omapper.JacksonJsonMapper;

public class RestRequestBuilder extends AbstractHttpRequestBuilder {

    private static final JacksonJsonMapper jsonMapper = new JacksonJsonMapper();

    @Override
    public String convert(Object o) {
        return jsonMapper.getValueAsString(o);
    }
}
