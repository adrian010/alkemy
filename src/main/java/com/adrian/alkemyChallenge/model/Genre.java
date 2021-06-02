package com.adrian.alkemyChallenge.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.adrian.alkemyChallenge.serializer.MovieSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



@Entity
@Table(name = "genre")
public class Genre implements Serializable{
	private static final long serialVersionUID = 6361680335920572326L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String image;

	@ManyToMany(mappedBy = "genre", fetch = FetchType.EAGER)
	@JsonSerialize(using= MovieSerializer.class)
	private Set<Movie> movies=new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}
	
	
	
}
