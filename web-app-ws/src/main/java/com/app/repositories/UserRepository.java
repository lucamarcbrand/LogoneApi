package com.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.io.entity.UserEntity;

@Repository
//Indicates that an annotated class is a "Repository", originally defined byDomain-Driven Design (Evans, 2003) as "a mechanism for encapsulating storage,retrieval, and search behavior which emulates a collection of objects
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	//below are custom methods to execute on UserEntity poperties
	
	//find user by email id
	UserEntity findByEmail(String email);
	
	//check if record exist in table for given email
	boolean existsByEmail(String email);
	
	//find user by public id
	UserEntity findByUserId(String userPublicID);
	
}
