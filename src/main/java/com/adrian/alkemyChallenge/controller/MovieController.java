package com.adrian.alkemyChallenge.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adrian.alkemyChallenge.apiError.ApiError;
import com.adrian.alkemyChallenge.dto.MovieDTO;
import com.adrian.alkemyChallenge.model.Character;
import com.adrian.alkemyChallenge.model.Genre;
import com.adrian.alkemyChallenge.model.Movie;
import com.adrian.alkemyChallenge.service.ICharacterService;
import com.adrian.alkemyChallenge.service.IGenreService;
import com.adrian.alkemyChallenge.service.IMovieService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	
	@Autowired
	private IMovieService movieService;
	
	@Autowired
	private ICharacterService characterService;
	
	@Autowired
	private IGenreService genreService;
	
	
	
	@ApiOperation(value = "Crear una pelicula", response = Movie.class)
	@ApiResponses(value = {
			 @ApiResponse(code = 201, message = "Pelicula creada correctamente"),
			 @ApiResponse(code = 400, message = "Id de Personaje o de Genero no existe"),
			 })
	@PostMapping	
	public ResponseEntity<?> create(@Valid @RequestBody Movie movie){
	
		for (Character character : movie.getCharacters()) {
			Optional<Character> charac = characterService.findById(character.getId());
			
			if(!charac.isPresent()) {
				
				ApiError apiError = new ApiError(String.format("id:%s on Character not found", character.getId()),HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST); 
				
				return new ResponseEntity<>(apiError,apiError.getStatus());
			}
		}
	
		for (Genre genre : movie.getGenre()) {
			Optional<Genre> gen = genreService.findById(genre.getId());
			
			if(!gen.isPresent()) {
				
				ApiError apiError = new ApiError(String.format("id:%s on Genre not found", genre.getId()),HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST); 
				
				return new ResponseEntity<>(apiError,apiError.getStatus());
			}
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(movieService.save(movie));
	}
	
	// Read 
	@ApiOperation(value = "Obtener una pelicula", response = Movie.class)
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long movieId){
		Optional<Movie> oMovie = movieService.findById(movieId);
		if(!oMovie.isPresent()) {		
			
			ApiError apiError = new ApiError(String.format("id:%s on Movie not found", movieId),HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND); 
			
			return new ResponseEntity<>(apiError,apiError.getStatus());
		}
			
		return ResponseEntity.ok(oMovie);
	}
		
	// Update
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Movie movieDetails, @PathVariable(value="id") Long movieId){
		Optional<Movie> movie = movieService.findById(movieId);
		
		if(!movie.isPresent()) {
			
			ApiError apiError = new ApiError(String.format("id:%s on Movie not found", movieId),HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND); 
			
			return new ResponseEntity<>(apiError,apiError.getStatus());		
		}
		movie.get().setImage(movieDetails.getImage());
		movie.get().setTitle(movieDetails.getTitle());
		movie.get().setDateCreated(movieDetails.getDateCreated());
		movie.get().setQualification(movieDetails.getQualification());
		
		for (Character character : movie.get().getCharacters()) {
			Optional<Character> charac = characterService.findById(character.getId());
			
			if(!charac.isPresent()) {
				
				ApiError apiError = new ApiError(String.format("id:%s on Character not found", character.getId()),HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST); 
				
				return new ResponseEntity<>(apiError,apiError.getStatus());
				
			}
		}
		
		for (Genre genre : movie.get().getGenre()) {
			Optional<Genre> gen = genreService.findById(genre.getId());
			
			if(!gen.isPresent()) {
				
				ApiError apiError = new ApiError(String.format("id:%s on Genre not found", genre.getId()),HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST); 
				
				return new ResponseEntity<>(apiError,apiError.getStatus());
			}
		}
		
		
		movie.get().setCharacters(movieDetails.getCharacters());		
		movie.get().setGenre(movieDetails.getGenre());
		
		return ResponseEntity.status(HttpStatus.OK).body(movieService.save(movie.get()));			
	}
	
	// Delete 
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable(value = "id") Long movieId){
				
		Optional<Movie> movie = movieService.findById(movieId);
		
		if(!movie.isPresent()) {
			
			ApiError apiError = new ApiError(String.format("id:%s on Movie not found", movieId),HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND); 
			
			return new ResponseEntity<>(apiError,apiError.getStatus());				
		}
		
		movie.get().getCharacters().forEach(character ->{
			character.getMovies().remove(movie.get());
		});
		
		movieService.deleteById(movieId);
		return ResponseEntity.ok().build();
	}
		
	//find all
	@GetMapping
	public List<MovieDTO> readAll(){
		List<MovieDTO> movie = movieService.findAll();
			
		return movie;
	}
	
	//find by title
	@ApiOperation(value = "Buscar una pelicula por titulo", response = Movie.class)
	@GetMapping(params = "title")
	public List<MovieDTO> findByTitle(@RequestParam String title){
		
		
		return movieService.findByTitle(title);
	}
	
	
	
	@GetMapping(params = "order")
	public ResponseEntity<?> findAllOrderByDateCreated(@RequestParam String order){
		
		List<MovieDTO> movies = new ArrayList<>();		
		
		if (order.equals("ASC")) {			
			movies = movieService.findAllByOrderByDateCreatedAsc();		
		} 
		else if(order.equals("DESC")) {		
			movies = movieService.findAllByOrderByDateCreatedDesc();		
		}
		else {			
			
			ApiError apiError = new ApiError(String.format("order: %s invalid. try ASC or DESC ", order),HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST); 
			
			return new ResponseEntity<>(apiError,apiError.getStatus());
			
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(movies);
	}	
}
