package com.test.technicalMarcos.model.repository;

import com.test.technicalMarcos.model.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IContactRepository extends CrudRepository<Contact, String> {

    List<Contact> findByUserId(String userId);

}
