package com.adrian.alkemyChallenge.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adrian.alkemyChallenge.dto.MovieDTO;
import com.adrian.alkemyChallenge.model.Movie;
import com.adrian.alkemyChallenge.repository.MovieRepository;

@Service
public class MovieServiceImpl implements IMovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Override
	@Transactional
	public Movie save(Movie movie) {

		return movieRepository.save(movie);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MovieDTO> findAll() {

		List<Movie> movieList = movieRepository.findAll();

		return movieList.stream().map(MovieDTO::new).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Movie> findById(Long id) {

		return movieRepository.findById(id);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {

		movieRepository.deleteById(id);
	}

	@Override
	@Transactional
	public List<MovieDTO> findByTitle(String title) {

		List<Movie> movieList = movieRepository.findByTitle(title);

		return movieList.stream().map(MovieDTO::new).collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public List<MovieDTO> findAllByOrderByDateCreatedAsc() {

		List<Movie> movieList = movieRepository.findAllByOrderByDateCreatedAsc();

		return movieList.stream().map(MovieDTO::new).collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public List<MovieDTO> findAllByOrderByDateCreatedDesc() {

		List<Movie> movieList = movieRepository.findAllByOrderByDateCreatedDesc();

		return movieList.stream().map(MovieDTO::new).collect(Collectors.toList());
	}
	
}
