package com.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.io.entity.InvoiceAddressEntity;

@Repository
public interface InvoiceRepository extends CrudRepository<InvoiceAddressEntity, Long>{

}
