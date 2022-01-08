package com.projects.java.social.repositories;

import com.projects.java.social.models.Buzz;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BuzzRepository extends CrudRepository<Buzz, Long> {
    
}
