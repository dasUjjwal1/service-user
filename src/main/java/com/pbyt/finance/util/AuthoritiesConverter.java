package com.pbyt.finance.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbyt.finance.entity.WorkArea;
import jakarta.persistence.AttributeConverter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthoritiesConverter implements AttributeConverter<Collection<? extends GrantedAuthority>,String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(Collection<? extends GrantedAuthority> workArea) {
        try {
            return objectMapper.writeValueAsString(workArea);
        } catch (JsonProcessingException jpe) {
            return null;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, Collection.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
