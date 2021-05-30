package com.adrian.alkemyChallenge.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adrian.alkemyChallenge.dto.CharacterDTO;
import com.adrian.alkemyChallenge.model.Character;
import com.adrian.alkemyChallenge.repository.CharacterRepository;

@Service
public class CharacterServiceImpl implements ICharacterService {

	@Autowired
	private CharacterRepository characterRepository;

	@Override
	@Transactional
	public Character save(Character character) {

		return characterRepository.save(character);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CharacterDTO> findAll() {

		List<Character> characterList = characterRepository.findAll();

		return characterList.stream().map(CharacterDTO::new).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Character> findById(Long id) {

		return characterRepository.findById(id);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {

		characterRepository.deleteById(id);
	}

	@Override
	@Transactional
	public List<CharacterDTO> findByName(String name) {

		List<Character> characterList = characterRepository.findByName(name);

		return characterList.stream().map(CharacterDTO::new).collect(Collectors.toList());

	}
	
	@Override
	@Transactional
	public List<CharacterDTO> findByAge(int age) {

		List<Character> CharacterList = characterRepository.findByAge(age);

		return CharacterList.stream().map(CharacterDTO::new).collect(Collectors.toList());

	}

	@Override
	@Transactional
	public List<CharacterDTO> findByMoviesId(Long movieId) {

		List<Character> characterList = characterRepository.findByMoviesId(movieId);

		return characterList.stream().map(CharacterDTO::new).collect(Collectors.toList());

	}
}
