package com.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.io.entity.InvoiceAddressEntity;

@Repository
//Indicates that an annotated class is a "Repository", originally defined byDomain-Driven Design (Evans, 2003) as "a mechanism for encapsulating storage,retrieval, and search behavior which emulates a collection of objects
public interface InvoiceRepository extends CrudRepository<InvoiceAddressEntity, Long>{

}
