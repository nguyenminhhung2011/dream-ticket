package com.ticket.server.controller;

import com.ticket.server.model.User;
import com.ticket.server.model.UserRegister;
import com.ticket.server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/user")
@AllArgsConstructor
public class UserController {
}
