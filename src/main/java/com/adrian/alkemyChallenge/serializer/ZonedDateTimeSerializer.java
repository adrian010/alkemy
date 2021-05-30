package com.adrian.alkemyChallenge.serializer;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime> {
	  static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

	  
	  public ZonedDateTimeSerializer() {
		  this(null);
	  }
	  
	  public ZonedDateTimeSerializer(Class<?> z) {
		  super();
	  }  
	  
	  @Override
	  public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider provider)
	          throws IOException {
	      try {
	          String s = value.format(DATE_FORMATTER);
	          gen.writeString(s);
	      } catch (DateTimeParseException e) {
	          System.err.println(e);
	          gen.writeString("");
	      }
	  }
	}