package com.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.io.entity.AddressEntity;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long>{

}
