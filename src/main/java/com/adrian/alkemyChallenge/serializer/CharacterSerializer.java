package com.adrian.alkemyChallenge.serializer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.adrian.alkemyChallenge.dto.CharacterDTO;
import com.adrian.alkemyChallenge.model.Character;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CharacterSerializer extends StdSerializer<Set<Character>> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CharacterSerializer(){
		this(null);
	}

    public CharacterSerializer(Class<Set<Character>> t) {
        super(t);
    }

    @Override
    public void serialize(
      Set<Character> personajes, 
      JsonGenerator generator, 
      SerializerProvider provider) 
      throws IOException, JsonProcessingException {
        
        Set<CharacterDTO> personajeDTO = new HashSet<>();
        for (Character personaje : personajes) {
        		CharacterDTO pj = new CharacterDTO(personaje);
        		personajeDTO.add(pj);
        }
        generator.writeObject(personajeDTO);
    }
}
