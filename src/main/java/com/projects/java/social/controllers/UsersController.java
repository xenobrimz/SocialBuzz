package com.projects.java.social.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.projects.java.social.models.Buzz;
import com.projects.java.social.models.User;
import com.projects.java.social.services.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {
    private final UserService userService;
    
    public UsersController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/register")
    public String registerForm(@ModelAttribute("user") User user) {
        return "register.jsp";
    }

    @RequestMapping("/login")
    public String registerForm() {
        return "login.jsp";
    }
    
    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        if(result.hasErrors()){
            return"register.jsp";
        }
        User u = userService.registerUser(user);    
        session.setAttribute("userId", u.getId() );
        return"redirect:/myProfile";
    }
    
    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        Boolean isAuthenticated = userService.authenticateUser(email, password);
        if(isAuthenticated){
            User u = userService.findByEmail(email);
            session.setAttribute("userId", u.getId());

            return"redirect:/myProfile";
        }
        model.addAttribute("error", "invalid credentials");
        return"login.jsp";
    }
    
    @GetMapping("/myProfile")
    public String home(HttpSession session, Model model, @ModelAttribute("buzz") Buzz buzz) {
        Long userId = (Long) session.getAttribute("userId");
        if(userId==null){
            return"redirect:/login";
        }
        if(session.getAttribute("buzzId")==null){

        }
        User u = userService.findUserById(userId);
        model.addAttribute("user", u);
        return"dashboard.jsp";
    }

    @PostMapping("/buzz/{id}/comment")
    public String postComment(HttpSession session, Model model, @ModelAttribute("buzz") Buzz buzz) {
        Long userId = (Long) session.getAttribute("userId");
        if(userId==null){
            return"redirect:/login";
        }
        if(session.getAttribute("buzzId")==null){

        }
        User u = userService.findUserById(userId);
        model.addAttribute("user", u);
        return"dashboard.jsp";
    }


    @GetMapping("/confirmDel")
    public String confirmDelete(HttpSession session){
        return"redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/zip/{id}")
    public String zipBuzz(@PathVariable("id") Long id, HttpSession session){
        String lastPage = (String)session.getAttribute("currentPage");
        Long uId = (Long)session.getAttribute("userId");

        userService.zipBuzz(id, uId );
        return "redirect:" + lastPage;
        
    }

    @GetMapping("/unzip/{id}")
    public String unzipBuzz(@PathVariable("id") Long id, HttpSession session){
        String lastPage = (String)session.getAttribute("currentPage");
        Long uId = (Long)session.getAttribute("userId");
        
        userService.unzipBuzz(id, uId );
        return "redirect:" + lastPage;
        
    }
}
