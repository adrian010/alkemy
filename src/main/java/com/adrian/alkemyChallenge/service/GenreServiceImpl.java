package com.adrian.alkemyChallenge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adrian.alkemyChallenge.model.Genre;
import com.adrian.alkemyChallenge.repository.GenreRepository;

@Service
public class GenreServiceImpl implements IGenreService {

	@Autowired
	private GenreRepository genreRepository;
	
	
	@Override
	@Transactional
	public Genre save(Genre genre) {

		return genreRepository.save(genre);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Genre> findById(Long id) {

		return genreRepository.findById(id);
	}
}
