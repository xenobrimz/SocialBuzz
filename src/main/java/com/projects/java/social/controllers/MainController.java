package com.projects.java.social.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.projects.java.social.models.Buzz;
import com.projects.java.social.models.User;
import com.projects.java.social.services.BuzzService;
import com.projects.java.social.services.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MainController {
    private final BuzzService buzzService;
    private final UserService userService;

    public MainController(BuzzService buzzService, UserService userService) {
        this.buzzService = buzzService;
        this.userService = userService;
    }
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        session.setAttribute("currentPage", "/");
        Long userId = (Long) session.getAttribute("userId");
        if(userId!= null){
            return "redirect:/feed";
        }
        return "index.jsp";
    }

    @GetMapping("/feed")
    public String feed(@ModelAttribute Buzz buzz, Model model, HttpSession session){
        session.setAttribute("currentPage", "/feed");

        List<Buzz> buzzes = buzzService.allBuzzes();
        Boolean isLogged = false;     

        Long userId = (Long) session.getAttribute("userId");
        
        if(userId!=null){
            isLogged = true;
            User u = userService.findUserById(userId);
            model.addAttribute("user", u); 
        }else{
            isLogged = false;
        }
        
        model.addAttribute("buzzes",buzzes);
        model.addAttribute("isLogged",isLogged);
        return "feed.jsp";
    }

    
}
