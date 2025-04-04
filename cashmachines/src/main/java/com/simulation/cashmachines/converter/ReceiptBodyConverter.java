package com.simulation.cashmachines.converter;

import java.io.IOException;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulation.cashmachines.entity.ReceiptBodyItem;
import com.fasterxml.jackson.databind.JavaType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ReceiptBodyConverter implements AttributeConverter<Map<String, ReceiptBodyItem>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, ReceiptBodyItem> attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Errore nella serializzazione della mappa", e);
        }
    }

    @Override
    public Map<String, ReceiptBodyItem> convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            JavaType type = objectMapper.getTypeFactory()
                .constructMapType(Map.class, String.class, ReceiptBodyItem.class);
            return objectMapper.readValue(dbData, type);
        } catch (IOException e) {
            throw new IllegalArgumentException("Errore nella deserializzazione della mappa", e);
        }
    }
}
