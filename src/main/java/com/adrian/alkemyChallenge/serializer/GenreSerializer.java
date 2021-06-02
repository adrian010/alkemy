package com.adrian.alkemyChallenge.serializer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.adrian.alkemyChallenge.dto.GenreDTO;
import com.adrian.alkemyChallenge.model.Genre;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class GenreSerializer extends StdSerializer<Set<Genre>>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GenreSerializer(){
		this(null);
	}

    public GenreSerializer(Class<Set<Genre>> t) {
        super(t);
    }
    
    @Override
    public void serialize(
      Set<Genre> genero, 
      JsonGenerator generator, 
      SerializerProvider provider) 
      throws IOException, JsonProcessingException {
        
        Set<GenreDTO> generoDTO = new HashSet<>();
        for (Genre gen : genero) {
        		GenreDTO genDTO = new GenreDTO(gen);
        		generoDTO.add(genDTO);
        }
        generator.writeObject(generoDTO);
    }
    
    
}
