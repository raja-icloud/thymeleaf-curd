package com.example.thymeleaf_curd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.thymeleaf_curd.models.User;
import com.example.thymeleaf_curd.repositories.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("users")
public class UserController {
    @Autowired UserRepository userRepository;

    @GetMapping("list")
    public String showList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user-list.html";
    }

    @GetMapping("form")
    public String showForm(@RequestParam(required = false) Long id, Model model) {
        User user = id == null  ? new User() : userRepository.findById(id).get();
        model.addAttribute("user", user);
        return "user-form.html";
    }

    @PostMapping("save")
    public String saveUser(@ModelAttribute User user) {
        if(user.getId() != null) { // Update
            User existingUser = userRepository.findById(user.getId()).get();
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            userRepository.save(user);
        } else { // Create
            userRepository.save(user);
        }
        return "redirect:/users/list";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/users/list";
    }
    
    
    
}
