package com.pbyt.finance.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbyt.finance.entity.Address;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AddressConverter implements AttributeConverter<Address, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Address address) {
        try {
            return objectMapper.writeValueAsString(address);
        } catch (JsonProcessingException jpe) {
            return null;
        }
    }

    @Override
    public Address convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, Address.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
