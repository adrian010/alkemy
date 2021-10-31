package com.adrian.alkemyChallenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrian.alkemyChallenge.dto.UserDTO;
import com.adrian.alkemyChallenge.model.User;
import com.adrian.alkemyChallenge.model.builder.UserBuilder;
import com.adrian.alkemyChallenge.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserRepository userRepository;
	/*
	@Autowired
	private ISendMailService sendMailService;
	*/
	@Override
	public User findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return user;
	}

	@Override
	public User save(UserDTO userDTO) {
		User user = new UserBuilder().withUserDTO(userDTO).build();
		user = userRepository.save(user);
		//sendMailService.sendEmail(user);
		return user;
	}
}
