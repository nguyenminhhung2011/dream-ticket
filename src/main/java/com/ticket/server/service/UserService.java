package com.ticket.server.service;


import com.ticket.server.model.User;
import com.ticket.server.model.UserRegister;
import com.ticket.server.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final String USER_NOT_FOUND = "user with email %s not found";
    private final UserRepository _userRepository;

    public UserService(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String username) {
        try {
            final Optional<User> user = _userRepository.findByUsername(username);

            if (user.isPresent()){
            }

        }catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND,username));
        }
        return null;
    }

    public User register(UserRegister userRegister){
        return null;
    }



}
