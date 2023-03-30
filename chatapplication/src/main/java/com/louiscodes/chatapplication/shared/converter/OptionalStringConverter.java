package com.louiscodes.chatapplication.shared.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

//this class is to allow for the Optional attribute to pass.

@Converter
public class OptionalStringConverter implements AttributeConverter<Optional<String>, String> {

    @Override
    public String convertToDatabaseColumn(Optional<String> attribute) {
        return attribute != null ? attribute.orElse(null) : null;
    }


    @Override
    public Optional<String> convertToEntityAttribute(String dbData) {
        return Optional.ofNullable(dbData);
    }
}
