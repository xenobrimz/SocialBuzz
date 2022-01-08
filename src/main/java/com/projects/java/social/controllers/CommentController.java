package com.projects.java.social.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.projects.java.social.models.Comment;
import com.projects.java.social.models.Buzz;
import com.projects.java.social.models.User;
import com.projects.java.social.services.CommentService;
import com.projects.java.social.services.BuzzService;
import com.projects.java.social.services.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    private final BuzzService buzzService;

    public CommentController(CommentService commentService, UserService userService, BuzzService buzzService) {
        this.commentService = commentService;
        this.userService = userService;
        this.buzzService = buzzService;
    }

    @GetMapping("buzz/comments/{id}")
    public String Comment(Model model, @PathVariable("id") Long id) {
        Comment Comment = commentService.findCommentById(id);
        model.addAttribute("Comment", Comment);
        return "Comment.jsp";
    }

    @GetMapping("buzz/comments/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        Comment Comment = commentService.findCommentById(id);
        System.out.println(Comment.getId());
        model.addAttribute("Comment", Comment);
        return "CommentEdit.jsp";
    }
    

    @PostMapping("buzz/comment/post")
    public String postComment(@Valid @ModelAttribute("Comments") Comment comment, BindingResult result, HttpSession session) {
        String currentPage = (String)session.getAttribute("currentPage");
        
        if(result.hasErrors()){
            return"redirect:" + currentPage;
        }

        Long userId = (Long) session.getAttribute("userId");
        User u = userService.findUserById(userId);

        comment.setUser(u);
        commentService.createComment(comment);
        return"redirect:/feed";
    }

    @PostMapping("buzz/comments/{id}/update")
    public String EditUser(@PathVariable("id") Long id, @Valid @ModelAttribute("Comment") Comment comm, BindingResult result, HttpSession session) {
        if(result.hasErrors()){
            return"CommentEdit.jsp";
        }

        Long userId = (Long) session.getAttribute("userId");
        User u = userService.findUserById(userId);
        comm.setUser(u);
        commentService.updateComment(comm);
        return"redirect:/home";
    }

    @GetMapping("buzz/comments/{id}/delete")
    public String deleteNinja(@PathVariable("id") Long id, HttpSession session){
        String currentPage = (String) session.getAttribute("currentPage");
        this.commentService.deleteComment(id);
        return"redirect:" + currentPage;
    }
}
