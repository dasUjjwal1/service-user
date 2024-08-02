package com.pbyt.finance.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.util.Collection;

public class AuthoritiesConverter implements AttributeConverter<Collection<String>,String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(Collection<String> workArea) {
        try {
            return objectMapper.writeValueAsString(workArea);
        } catch (JsonProcessingException jpe) {
            return null;
        }
    }

    @Override
    public Collection<String> convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, Collection.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
