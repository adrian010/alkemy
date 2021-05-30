package com.adrian.alkemyChallenge.dto;

import com.adrian.alkemyChallenge.model.Genre;

public class GenreDTO {

	private String name;	
	private String image;

	
	public GenreDTO(Genre genre) {
		this.name=genre.getName();
		this.image=genre.getImage();
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
	
	
	
	
}
