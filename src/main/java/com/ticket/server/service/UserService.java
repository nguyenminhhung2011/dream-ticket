package com.ticket.server.service;

import com.ticket.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ticket.server.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        if(user.getAccountName() == null || user.getAccountName().isEmpty()){
            return null;
        }
        if(user.getPassword() == null || user.getPassword().isEmpty()){
            return null;
        }

        return userRepository.save(user);
    }
}

