package com.adrian.alkemyChallenge.deserializer;

import java.io.IOException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

    @Override
    public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
    		throws IOException {

    		ZonedDateTime zonedDateTime = ZonedDateTime.parse(jsonParser.getText(),DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    		return zonedDateTime;
    }
}