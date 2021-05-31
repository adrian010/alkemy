package com.adrian.alkemyChallenge.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.adrian.alkemyChallenge.serializer.GenreSerializer;
import com.adrian.alkemyChallenge.serializer.ZonedDateTimeSerializer;
import com.adrian.alkemyChallenge.deserializer.ZonedDateTimeDeserializer;
import com.adrian.alkemyChallenge.serializer.CharacterSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
@Table(name = "movies")
public class Movie implements Serializable{

	private static final long serialVersionUID = 6361680335920572326L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String image;
	
	private String title;
	
	@JsonDeserialize(using = ZonedDateTimeDeserializer.class)
	@JsonSerialize(using = ZonedDateTimeSerializer.class)
	@JsonProperty("date_created")
	private ZonedDateTime dateCreated;
	
	@Min(value = 1)
	@Max(value= 5)
	private int qualification;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name="movie_character"
			,joinColumns = {@JoinColumn(name="movie_id")}
			,inverseJoinColumns = {@JoinColumn(name="character_id")}
	)
	@JsonSerialize(using = CharacterSerializer.class)
	private Set<Character> characters= new HashSet<>();
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name="movie_genre"
			,joinColumns = {@JoinColumn(name="movie_id")}
			,inverseJoinColumns = {@JoinColumn(name="genre_id")}
	)
	@JsonSerialize(using = GenreSerializer.class)
	private Set<Genre> genre= new HashSet<>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}


	public int getQualification() {
		return qualification;
	}


	public void setQualification(int qualification) {
		this.qualification = qualification;
	}


	public Set<Character> getCharacters() {
		return characters;
	}


	public void setCharacters(Set<Character> characters) {
		this.characters = characters;
	}


	public Set<Genre> getGenre() {
		return genre;
	}


	public void setGenre(Set<Genre> genre) {
		this.genre = genre;
	}

}
