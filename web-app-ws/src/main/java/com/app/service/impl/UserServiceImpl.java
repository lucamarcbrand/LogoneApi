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
	public UserDto createUser(UserDto userDto) {
		String userId = randomIdGenUtil.generateUserID(30);
		if (userRepository.existsByEmail(userDto.getEmail())) {
			throw new UserServiceException("USER WITH SAME EMIL ID IS ALEADY EXISTS ,PLEASE TRY WITH ANOTHER EMAIL");
		}

		userDto.setUserId(userId);

//		if(userDto.getAddress() !=null) {
//			userDto.getAddress().setAddrressId(randomIdGenUtil.generateAddressID(30));
//		}
//		else {
//			throw new UserServiceException("Address Deails not provided");
//		}

		UserEntity userEntity = new UserEntity();
		mp.map(userDto, userEntity);

		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

		UserEntity storedUserDetail = userRepository.save(userEntity);

		UserDto createdUser = new UserDto();
		mp.map(storedUserDetail, createdUser);

		return createdUser;
	}

	@Override
	public UserDetails loadUserByUsername(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		return new User(email, userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String userEmail) {
		UserEntity userEntity = userRepository.findByEmail(userEmail);
		if (userEntity == null)
			throw new UsernameNotFoundException(userEmail);
		UserDto returnObj = new UserDto();
		mp.map(userEntity, returnObj);
		return returnObj;
	}

	@Override
	public UserDto getUserDetailsById(String userPublicID) {
		UserEntity userEntity = userRepository.findByUserId(userPublicID);
		if (userEntity == null)
			throw new UsernameNotFoundException(userPublicID);
		UserDto returnObj = new UserDto();
		mp.map(userEntity, returnObj);
		return returnObj;
	}

	@Override
	public boolean deleteUser(String userPublicID) {
		UserEntity userEntity = userRepository.findByUserId(userPublicID);
		if (userEntity == null)
			throw new UsernameNotFoundException(userPublicID);
		userRepository.delete(userEntity);
		return true;
	}

	@Override
	public UserDto updateUser(String userPublicID, UserDto userDto, boolean changePassword) {

		UserEntity userEntity = new UserEntity();

		AddressDto address = userDto.getAddress();
		InvoiceAddressDto invoiceAddress = userDto.getInvoiceAddress();

		AddressEntity addresseEntity = new AddressEntity();
		InvoiceAddressEntity invoiceEntity = new InvoiceAddressEntity();

		mp.map(address, addresseEntity);
		mp.map(invoiceAddress, invoiceEntity);

		if (changePassword) {
			userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		}

		mp.map(userDto, userEntity);
		userEntity.setAddress(addresseEntity);
		userEntity.setInvoiceAddress(invoiceEntity);
		userRepository.save(userEntity);

		mp.map(userEntity, userDto);
		return userDto;
	}

}
