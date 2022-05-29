package com.app.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto userDto);
	UserDto updateUser(String userPublicID,UserDto userDto,boolean changePassword);
	UserDto getUser(String email);
	UserDto getUserDetailsById(String userPublicID);
	boolean deleteUser(String userPublicID);

}
