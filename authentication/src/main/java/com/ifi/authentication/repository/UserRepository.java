package com.ifi.authentication.repository;

import com.ifi.authentication.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String>{

    public User findUserByUserNameAndPassword(String userName, String password);

}
