package com.adrian.alkemyChallenge.controller;

import java.util.List;
import java.util.Optional;

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

import com.adrian.alkemyChallenge.dto.CharacterDTO;
import com.adrian.alkemyChallenge.model.Character;
import com.adrian.alkemyChallenge.service.ICharacterService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/characters")
public class CharacterController {

	@Autowired
	private ICharacterService characterService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Character character){
		return ResponseEntity.status(HttpStatus.CREATED).body(characterService.save(character));
	}
	
	// Read 
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long characterId){
	//public ResponseEntity<?> read(@PathVariable(value = "id") Long characterId){
		Optional<Character> oCharacter = characterService.findById(characterId);
		if(!oCharacter.isPresent()) {
			return ResponseEntity.notFound().build();
		}
				
		return ResponseEntity.ok(oCharacter);
	}
	
	// Update
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Character characterDetails, @PathVariable(value="id") Long characterId){
		Optional<Character> character = characterService.findById(characterId);
			
		if(!character.isPresent()) {
			return ResponseEntity.notFound().build();			
		}
			
		character.get().setImage(characterDetails.getImage());
		character.get().setName(characterDetails.getName());
		character.get().setAge(characterDetails.getAge());
		character.get().setHeight(characterDetails.getHeight());
		character.get().setHistory(characterDetails.getHistory());
		
		return ResponseEntity.status(HttpStatus.OK).body(characterService.save(character.get()));			
	}
	
	// Delete 
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@ApiParam(value = "ID of person to return", required = true, example = "123") @PathVariable(value = "id") Long characterId){
				
		Optional<Character> character = characterService.findById(characterId);
		
		if(!character.isPresent()) {
			return ResponseEntity.notFound().build();			
		}
		
		character.get().getMovies().forEach(movie ->{
			movie.getCharacters().remove(character.get());
		});
		
		
			characterService.deleteById(characterId);
		return ResponseEntity.ok().build();
	}
			
	// Read all Users
	@GetMapping
	public List<CharacterDTO> readAll(){
		List<CharacterDTO> character = characterService.findAll();
				
		return character;
	}
	
	
	@GetMapping(params = "name")
	@ResponseBody
	public List<CharacterDTO> findByName(@ApiParam(value = "Busca un Personaje por Nombre", required = true) @RequestParam(value = "name") String name){
		
		
		return characterService.findByName(name);
	}
	
	@GetMapping(params = "age")
	@ResponseBody
	public List<CharacterDTO> findByAge(@ApiParam(value = "Busca un Personaje por Edad", required = true, example = "123") @RequestParam(value = "age") int age){
		
		return characterService.findByAge(age);
	}
	
	@GetMapping(params = "movies")
	@ResponseBody
	public List<CharacterDTO> findByMoviesId(@ApiParam(value = "Busca un Personaje por ID de Pelicula", required = true, example = "123") @RequestParam Long movies){
		
		return characterService.findByMoviesId(movies);
	}
	
}
