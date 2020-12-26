package com.kai.api.repository;

import com.kai.api.model.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, String> {

    User findFirstByUsername(String username);
}