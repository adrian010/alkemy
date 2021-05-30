package com.adrian.alkemyChallenge.dto;

import com.adrian.alkemyChallenge.model.Character;

public class CharacterDTO {
	
	private String image;
	private String name;

	
	public CharacterDTO(Character character) {
		this.image=character.getImage();
		this.name=character.getName();
		
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	
	





	
	
}
