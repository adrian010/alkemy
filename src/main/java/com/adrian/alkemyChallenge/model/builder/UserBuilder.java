package com.adrian.alkemyChallenge.model.builder;

import com.adrian.alkemyChallenge.dto.UserDTO;
import com.adrian.alkemyChallenge.model.User;

public class UserBuilder {

	private String name;
	private String lastName;
	private String mail;
	private String username;
	private String password;
	
	public UserBuilder withUserDTO(UserDTO userDTO) {
		this.name = userDTO.getName();
		this.lastName = userDTO.getLastName();
		this.mail = userDTO.getMail();
		this.username = userDTO.getUsername();
		this.password = userDTO.getPassword();
		return this;
	}
	
	public User build() {
		return new User(name, lastName, mail, username, password);
	}
}
