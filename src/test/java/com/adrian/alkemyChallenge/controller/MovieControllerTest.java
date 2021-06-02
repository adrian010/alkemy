package com.adrian.alkemyChallenge.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.adrian.alkemyChallenge.dto.MovieDTO;
import com.adrian.alkemyChallenge.model.Movie;
import com.adrian.alkemyChallenge.service.MovieServiceImpl;

@WebMvcTest(controllers = MovieController.class)
@ActiveProfiles("test")


public class MovieControllerTest {
	@Autowired
	private MockMvc mockMvc;  
	                                                 
	@MockBean
	private MovieServiceImpl movieService;
	                                               
	private List<MovieDTO> movieList;       
	                                            
	@BeforeEach                           
	void setUp() {                               
	   this.movieList = new ArrayList<>();
	   Movie movie = new Movie();
	   movie.setImage("jojo.jpg");
	   movie.setDateCreated(ZonedDateTime.now());
	   movie.setTitle("Jojo");
	   
	   MovieDTO movieDTO = new MovieDTO(movie);
	   this.movieList.add(movieDTO);  
	       //this.userList.add(new User(2L, "user2@gmail.com", "pwd2","User2")); 
	       //this.userList.add(new User(3L, "user3@gmail.com", "pwd3","User3"));                                                       
	 
	}
	
	@Test
 	void shouldFetchAllUsers() throws Exception {
		Mockito.when(movieService.findAll()).thenReturn(movieList);	 	
		
        this.mockMvc.perform(get("/movies"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()", is(movieList.size())));
 	}
	
	@Test
 	void shouldThrowBadRequest() throws Exception {		 	

        this.mockMvc.perform(get("/movies?order=fafafa"))
		.andExpect(status().isBadRequest());
		
 	}
	
	@Test
 	void shouldSortAsc() throws Exception {		 	
		Mockito.when(movieService.findAllByOrderByDateCreatedAsc()).thenReturn(movieList);
		
        this.mockMvc.perform(get("/movies?order=ASC"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()", is(movieList.size())));
	}
	
	@Test
 	void shouldSortDesc() throws Exception {		 	
		Mockito.when(movieService.findAllByOrderByDateCreatedDesc()).thenReturn(movieList);
		
        this.mockMvc.perform(get("/movies?order=DESC"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()", is(movieList.size())));
		
 	}
}
