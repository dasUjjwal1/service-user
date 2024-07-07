package com.pbyt.finance.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbyt.finance.entity.WorkArea;
import jakarta.persistence.AttributeConverter;

public class WorkAreaConverter implements AttributeConverter<WorkArea, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(WorkArea workArea) {
        try {
            return objectMapper.writeValueAsString(workArea);
        } catch (JsonProcessingException jpe) {
            return null;
        }
    }

    @Override
    public WorkArea convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, WorkArea.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
