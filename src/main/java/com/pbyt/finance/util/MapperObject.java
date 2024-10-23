package com.pbyt.finance.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbyt.finance.applicationModel.WorkArea;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapperObject<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String convertToDatabaseColumn(T attribute) {

        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException jpe) {
            return null;
        }
    }

    public T convertToEntityAttribute(String dbData) {
        try {
            return  objectMapper.readValue(dbData, new TypeReference<T>() {
            });
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
