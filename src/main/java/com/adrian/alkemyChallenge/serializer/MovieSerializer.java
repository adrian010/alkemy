package com.adrian.alkemyChallenge.serializer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.adrian.alkemyChallenge.dto.MovieDTO;
import com.adrian.alkemyChallenge.model.Movie;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class MovieSerializer extends StdSerializer<Set<Movie>> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovieSerializer(){
		this(null);
	}

    public MovieSerializer(Class<Set<Movie>> t) {
        super(t);
    }

    @Override
    public void serialize(
      Set<Movie> peliculas, 
      JsonGenerator generator, 
      SerializerProvider provider) 
      throws IOException, JsonProcessingException {
        
        Set<MovieDTO> peliculaDTO = new HashSet<>();
        for (Movie pelicula : peliculas) {
        		MovieDTO peli = new MovieDTO(pelicula);
        		peliculaDTO.add(peli);
        }
        generator.writeObject(peliculaDTO);
    }

}
