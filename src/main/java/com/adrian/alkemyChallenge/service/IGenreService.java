package com.adrian.alkemyChallenge.service;

import java.util.Optional;

import com.adrian.alkemyChallenge.model.Genre;

public interface IGenreService {

	public Genre save(Genre genre);
	
	public Optional<Genre> findById(Long id);
}
