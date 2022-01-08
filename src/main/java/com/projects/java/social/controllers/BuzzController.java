package com.projects.java.social.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.projects.java.social.models.Buzz;
import com.projects.java.social.models.User;
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
public class BuzzController {
    private final BuzzService buzzService;
    private final UserService userService;

    public BuzzController(BuzzService buzzService, UserService userService) {
        this.buzzService = buzzService;
        this.userService = userService;
    }

    @GetMapping("/Buzzes")
    public String BuzzForm(Model model, @ModelAttribute("Buzz") Buzz Buzz) {
        return "BuzzForm.jsp";
    }

    @GetMapping("/Buzzes/{id}")
    public String Buzz(Model model, @PathVariable("id") Long id) {
        Buzz Buzz = buzzService.findBuzzById(id);
        model.addAttribute("Buzz", Buzz);
        return "Buzz.jsp";
    }

    @GetMapping("/Buzzes/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        Buzz Buzz = buzzService.findBuzzById(id);
        System.out.println(Buzz.getId());
        model.addAttribute("Buzz", Buzz);
        return "BuzzEdit.jsp";
    }
    
    @PostMapping("/myProfile/post")
    public String myProfilePost(@Valid @ModelAttribute("Buzz") Buzz buzz, BindingResult result, HttpSession session) {
        if(result.hasErrors()){
            return"BuzzForm.jsp";
        }
        Long userId = (Long) session.getAttribute("userId");
        User u = userService.findUserById(userId);
        buzz.setUser(u);
        buzzService.createBuzz(buzz);
        return"redirect:/myProfile";
    }

    @PostMapping("/feed/post")
    public String feedPost(@Valid @ModelAttribute("Buzz") Buzz buzz, BindingResult result, HttpSession session) {
        if(result.hasErrors()){
            return"BuzzForm.jsp";
        }
        Long userId = (Long) session.getAttribute("userId");
        User u = userService.findUserById(userId);
        buzz.setUser(u);
        buzzService.createBuzz(buzz);
        return"redirect:/feed";
    }

    @PostMapping("/Buzzes/{id}/update")
    public String EditUser(@PathVariable("id") Long id, @Valid @ModelAttribute("Buzz") Buzz Buzz, BindingResult result, HttpSession session) {
        if(result.hasErrors()){
            return"BuzzEdit.jsp";
        }

        Long userId = (Long) session.getAttribute("userId");
        User u = userService.findUserById(userId);
        Buzz.setUser(u);
        buzzService.updateBuzz(Buzz);
        return"redirect:/home";
    }

    @GetMapping("/{id}/delete")
    public String deleteNinja(@PathVariable("id") Long id){
        this.buzzService.deleteBuzz(id);
        return"redirect:/myProfile";
    }
}
