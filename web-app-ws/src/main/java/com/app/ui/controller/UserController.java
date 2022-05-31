package com.app.ui.controller;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.UserService;
import com.app.service.exception.UserServiceException;
import com.app.shared.utility.ErrorMessages;
import com.shared.dto.AddressDto;
import com.shared.dto.InvoiceAddressDto;
import com.shared.dto.UserDto;
import com.ui.model.request.AddressDetailRequestModel;
import com.ui.model.request.InvoiceAddressModal;
import com.ui.model.request.UserDetailRequestModel;
import com.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
// user related request will be handled by this controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;
	ModelMapper modelmapper = new ModelMapper();

	@GetMapping(path = "/getUserDetail/{userPublicId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	// get user information bby passing userId
	public UserRest getUserDetail(@PathVariable String userPublicId) {
		logger.info("UserController.getUserDetail for User public id" + userPublicId);
		UserRest userRest = new UserRest();
		UserDto userDto = userService.getUserDetailsById(userPublicId);
		modelmapper.map(userDto, userRest);
		userRest.setPassword(null);
		return userRest;
	}

	@PostMapping(path = "/createUser", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	//this method is used to create user
	public UserRest createUserDetail(@RequestBody UserDetailRequestModel userDetailRequestModel) throws Exception {
		UserRest userRest = new UserRest();

		UserDto userDto = modelmapper.map(userDetailRequestModel, UserDto.class);
		UserDto createdUser = null;
		try {
			// actual DB call to creaate user
			createdUser = userService.createUser(userDto);
			modelmapper.map(createdUser, userRest);
		} catch (UserServiceException userException) {
			userException.printStackTrace();
			throw new UserServiceException(userException.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserServiceException(ErrorMessages.UNKNOWN_EXCEPION_OCCUED.getErrorMessage());
		}

		return userRest;
	}

	@PutMapping	(path = "/updateUserDetail", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	// updates user profile information
	public UserRest updateUserDetail(@RequestBody UserDetailRequestModel userDetailRequestModel) {
		String userPublicId =userDetailRequestModel.getUserId();
		boolean changePassword=userDetailRequestModel.isChangePassword();
		if (StringUtils.isEmpty(userPublicId)) {
			throw new UserServiceException(ErrorMessages.PROVIDE_USER_ID.getErrorMessage());
		}
		UserRest userResponse = new UserRest();
		UserDto userDto = userService.getUserDetailsById(userPublicId);
		
		AddressDetailRequestModel address = userDetailRequestModel.getAddress();
		InvoiceAddressModal invoiceAddress = userDetailRequestModel.getInvoiceAddress();
		
		AddressDto addressDto= new AddressDto();
		InvoiceAddressDto invoiceDto = new InvoiceAddressDto();
		
		modelmapper.map(address, addressDto);
		modelmapper.map(invoiceAddress, invoiceDto);
		
		//set user address 
		if(userDto.getAddress() !=null) {
			addressDto.setId(userDto.getAddress().getId());
		}
		// set invoice address id
		if(userDto.getInvoiceAddress() !=null) {
			invoiceDto.setId(userDto.getInvoiceAddress().getId());
		}
		// set details captured from API request to DTO
		userDto.setAddress(addressDto);
		userDto.setInvoiceAddress(invoiceDto);
		userDto.setFirstName(userDetailRequestModel.getFirstName());
		userDto.setLastName(userDetailRequestModel.getLastName());
		userDto.setMobileNumber(userDetailRequestModel.getMobileNumber());
		if(changePassword) {
			userDto.setPassword(userDetailRequestModel.getPassword());
		}
		// send users updated information to service layer
		UserDto updateUserResponse = userService.updateUser(userPublicId,userDto,changePassword);
		modelmapper.map(updateUserResponse,userResponse);
		userResponse.setPassword(null);
		return userResponse;
	}

	@DeleteMapping(path = "/deleteUser/{userId}")
	// this service is to delete user
	public String deleteUserDetail(@PathVariable String userId) {
		if (StringUtils.isEmpty(userId)) {
			throw new UserServiceException(ErrorMessages.PROVIDE_USER_ID.getErrorMessage());
		}

		return userService.deleteUser(userId) ? "deleted Successfullty" : "Issue occured while deleting record";
	}
}
