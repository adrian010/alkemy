package com.adrian.alkemyChallenge.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adrian.alkemyChallenge.model.Genre;
import com.adrian.alkemyChallenge.service.IGenreService;

@RestController
@RequestMapping("/genre")
public class GenreController {
	
	@Autowired
	private IGenreService genreService;
	
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Genre genre){
		return ResponseEntity.status(HttpStatus.CREATED).body(genreService.save(genre));
	}
	
	// Read 
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long genreId){
		Optional<Genre> oGenre = genreService.findById(genreId);
		if(!oGenre.isPresent()) {
			return ResponseEntity.notFound().build();
		}
				
		return ResponseEntity.ok(oGenre);
	}
	
	// Update
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Genre genreDetails, @PathVariable(value="id") Long genreId){
		Optional<Genre> genre = genreService.findById(genreId);
			
		if(!genre.isPresent()) {
			return ResponseEntity.notFound().build();			
		}
			
		genre.get().setImage(genreDetails.getImage());
		genre.get().setName(genreDetails.getName());
		
		return ResponseEntity.status(HttpStatus.OK).body(genreService.save(genre.get()));			
	}
	

}
