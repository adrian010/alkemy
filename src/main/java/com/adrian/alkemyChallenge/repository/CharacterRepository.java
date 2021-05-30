package com.adrian.alkemyChallenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.adrian.alkemyChallenge.model.Character;

public interface CharacterRepository extends JpaRepository<Character, Long>{
	
	List<Character> findByName(String name);
	
	List<Character> findByAge(int edad);
	
	List<Character> findByMoviesId(Long peliculaId);

}
