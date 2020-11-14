package com.test.technicalMarcos.model.repository;

import com.test.technicalMarcos.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, String> {
}
