package com.adrian.alkemyChallenge.service;

import com.adrian.alkemyChallenge.dto.UserDTO;
import com.adrian.alkemyChallenge.model.User;

public interface IUserService {

	public User findByUsername(String username);
	public User save(UserDTO userDTO);
}
