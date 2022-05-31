package com.app.service.impl;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.io.entity.AddressEntity;
import com.app.io.entity.InvoiceAddressEntity;
import com.app.io.entity.UserEntity;
import com.app.repositories.UserRepository;
import com.app.service.UserService;
import com.app.service.exception.UserServiceException;
import com.app.shared.utility.RandomIdGenUtil;
import com.shared.dto.AddressDto;
import com.shared.dto.InvoiceAddressDto;
import com.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RandomIdGenUtil randomIdGenUtil;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private ModelMapper mp = new ModelMapper();

	@Override
	// create new application user
	public UserDto createUser(UserDto userDto) {
		String userId = randomIdGenUtil.generateUserID(30);
		// check if user already exists
		if (userRepository.existsByEmail(userDto.getEmail())) {
			throw new UserServiceException("USER WITH SAME EMIL ID IS ALEADY EXISTS ,PLEASE TRY WITH ANOTHER EMAIL");
		}

		userDto.setUserId(userId);

		UserEntity userEntity = new UserEntity();
		mp.map(userDto, userEntity);
// convert text password to encripted format and then store in DATA BASE
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

		UserEntity storedUserDetail = userRepository.save(userEntity);

		UserDto createdUser = new UserDto();
		mp.map(storedUserDetail, createdUser);

		return createdUser;
	}

	@Override
	// find user from Data base using email id
	public UserDetails loadUserByUsername(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		return new User(email, userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String userEmail) {
		// find user from Data base using email id
		UserEntity userEntity = userRepository.findByEmail(userEmail);
		if (userEntity == null)
			throw new UsernameNotFoundException(userEmail);
		UserDto returnObj = new UserDto();
		mp.map(userEntity, returnObj);
		return returnObj;
	}

	@Override
	public UserDto getUserDetailsById(String userPublicID) {
		// find user from Data base using user id
		UserEntity userEntity = userRepository.findByUserId(userPublicID);
		if (userEntity == null)
			throw new UsernameNotFoundException(userPublicID);
		UserDto returnObj = new UserDto();
		mp.map(userEntity, returnObj);
		return returnObj;
	}

	@Override
	// delete user from Application
	// it requires user id 
	public boolean deleteUser(String userPublicID) {
		// find user from Data base
		UserEntity userEntity = userRepository.findByUserId(userPublicID);
		if (userEntity == null)
			throw new UsernameNotFoundException(userPublicID);
		
		// delete user from Database
		userRepository.delete(userEntity);
		return true;
	}

	@Override
	//Update the user profile information
	public UserDto updateUser(String userPublicID, UserDto userDto, boolean changePassword) {

		UserEntity userEntity = new UserEntity();

		AddressDto address = userDto.getAddress();
		InvoiceAddressDto invoiceAddress = userDto.getInvoiceAddress();

		AddressEntity addresseEntity = new AddressEntity();
		InvoiceAddressEntity invoiceEntity = new InvoiceAddressEntity();

		mp.map(address, addresseEntity);
		mp.map(invoiceAddress, invoiceEntity);
// change user password if "changePassword" flag is true
		if (changePassword) {
			userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		}

		mp.map(userDto, userEntity);
		userEntity.setAddress(addresseEntity);
		userEntity.setInvoiceAddress(invoiceEntity);
		// save the updated user details in Database
		userRepository.save(userEntity);

		mp.map(userEntity, userDto);
		return userDto;
	}

}
