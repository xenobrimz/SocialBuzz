package com.projects.java.social.repositories;
import com.projects.java.social.models.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}

