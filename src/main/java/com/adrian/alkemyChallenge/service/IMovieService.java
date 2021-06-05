package com.adrian.alkemyChallenge.service;

import java.util.List;
import java.util.Optional;

import com.adrian.alkemyChallenge.dto.MovieDTO;
import com.adrian.alkemyChallenge.model.Movie;

public interface IMovieService {

	public List<MovieDTO> findAll();

	public Movie save(Movie movie);

	public Optional<Movie> findById(Long id);

	public void deleteById(Long id);

	public List<MovieDTO> findByTitle(String title);
	
	public List<MovieDTO> findAllByOrderByDateCreatedAsc();
	
	public List<MovieDTO> findAllByOrderByDateCreatedDesc();
	
	public List<MovieDTO> findByGenreId(Long genreId);
}
