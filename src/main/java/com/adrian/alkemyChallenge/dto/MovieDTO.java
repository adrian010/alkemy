package com.adrian.alkemyChallenge.dto;

import java.time.ZonedDateTime;

import com.adrian.alkemyChallenge.deserializer.ZonedDateTimeDeserializer;
import com.adrian.alkemyChallenge.model.Movie;
import com.adrian.alkemyChallenge.serializer.ZonedDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class MovieDTO {
	
	
	private String image;
	private String title;
	
	@JsonDeserialize(using = ZonedDateTimeDeserializer.class)
	@JsonSerialize(using = ZonedDateTimeSerializer.class)
	@JsonProperty("date_created")
	private ZonedDateTime dateCreated;
	
	
	public MovieDTO(Movie movie) {
		this.image=movie.getImage();
		this.title=movie.getTitle();
		this.dateCreated=movie.getDateCreated();
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
	
	
	
	
}
