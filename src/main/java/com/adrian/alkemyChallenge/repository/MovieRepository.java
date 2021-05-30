package com.adrian.alkemyChallenge.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.adrian.alkemyChallenge.model.Movie;


public interface MovieRepository extends JpaRepository<Movie, Long>{

	List<Movie> findByTitle(String title);
	
	List<Movie> findAllByOrderByDateCreatedAsc();
	
	List<Movie> findAllByOrderByDateCreatedDesc();
}
