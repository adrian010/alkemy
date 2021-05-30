package com.adrian.alkemyChallenge.service;

import java.util.List;
import java.util.Optional;

import com.adrian.alkemyChallenge.dto.CharacterDTO;
import com.adrian.alkemyChallenge.model.Character;

public interface ICharacterService {

	public Character save(Character character);

	public List<CharacterDTO> findAll();

	public Optional<Character> findById(Long id);

	public void deleteById(Long id);

	public List<CharacterDTO> findByName(String name);
	
	public List<CharacterDTO> findByAge(int age);
	
	public List<CharacterDTO> findByMoviesId(Long movieId);

}
