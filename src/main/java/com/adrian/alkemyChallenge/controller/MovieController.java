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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adrian.alkemyChallenge.dto.MovieDTO;
import com.adrian.alkemyChallenge.model.Movie;
import com.adrian.alkemyChallenge.service.IMovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	
	@Autowired
	private IMovieService movieService;
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Movie movie){
		return ResponseEntity.status(HttpStatus.CREATED).body(movieService.save(movie));
	}
	
	// Read 
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long movieId){
		Optional<Movie> oMovie = movieService.findById(movieId);
		if(!oMovie.isPresent()) {
			return ResponseEntity.notFound().build();
		}
			
		return ResponseEntity.ok(oMovie);
	}
		
	// Update
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Movie movieDetails, @PathVariable(value="id") Long movieId){
		Optional<Movie> movie = movieService.findById(movieId);
		
		if(!movie.isPresent()) {
			return ResponseEntity.notFound().build();			
		}
		
		movie.get().setImage(movieDetails.getImage());
		movie.get().setTitle(movieDetails.getTitle());
		movie.get().setDateCreated(movieDetails.getDateCreated());
		movie.get().setQualification(movieDetails.getQualification());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(movieService.save(movie.get()));			
	}
	
	// Delete 
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable(value = "id") Long movieId){
				
		Optional<Movie> movie = movieService.findById(movieId);
		
		if(!movie.isPresent()) {
			return ResponseEntity.notFound().build();			
		}
		
		movie.get().getCharacters().forEach(character ->{
			character.getMovies().remove(movie.get());
		});
		
		
		
		movieService.deleteById(movieId);
		return ResponseEntity.ok().build();
	}
		
	// Read all Users
	@GetMapping
	public List<MovieDTO> readAll(){
		List<MovieDTO> movie = movieService.findAll();
			
		return movie;
	}
	
	
	@GetMapping(params = "title")
	@ResponseBody
	public List<MovieDTO> findByTitulo(@RequestParam String title){
		
		
		return movieService.findByTitle(title);
	}
	
	@GetMapping(params = "order")
	@ResponseBody
	public ResponseEntity<?> findAllOrderByDateCreated(@RequestParam String order){
		
		List<MovieDTO> movies = new ArrayList<>();		
		
		if (order.equals("ASC")) {			
			movies = movieService.findAllByOrderByDateCreatedAsc();		
		} 
		else if(order.equals("DESC")) {		
			movies = movieService.findAllByOrderByDateCreatedDesc();		
		}
		else {			
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(movies);
	}
		
		
}
