package com.projects.java.social.services;

import java.util.List;
import java.util.Optional;

import com.projects.java.social.models.Comment;
import com.projects.java.social.repositories.CommentRepository;

import org.springframework.stereotype.Service;


@Service
public class CommentService {
    private final CommentRepository commRepo;

    public CommentService(CommentRepository commRepo){
        this.commRepo = commRepo;
    }

    public List<Comment> allComments(){
        return (List<Comment>)commRepo.findAll();
    }

    public Comment createComment(Comment d){
        return this.commRepo.save(d);
    }

    public Comment findCommentById(Long id){
        Optional<Comment> optionalComment = commRepo.findById(id);
        if(optionalComment.isPresent()){
            return optionalComment.get();
        }
        return null;
    }

    public Comment updateComment(Comment b){
        
        return commRepo.save(b);
    }

    public void deleteComment(Long id){
        commRepo.deleteById(id);
    }

    
}
