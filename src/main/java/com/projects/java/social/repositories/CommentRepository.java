package com.projects.java.social.repositories;

import com.projects.java.social.models.Comment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    
}
