package com.ticket.server.controller;

import com.ticket.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ticket.server.service.UserService;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return null;
    }


}
