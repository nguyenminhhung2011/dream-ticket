package com.ticket.server.service;


import com.ticket.server.model.User;
import com.ticket.server.model.UserRegister;
import com.ticket.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final String USER_NOT_FOUND = "user with email %s not found";
    private final UserRepository _userRepository;
    @Override
    public User loadUserByUsername(String username) {
        try {
            Optional<User> optionalUser = _userRepository.findByUsername(username);
            if (optionalUser.isPresent()){
                return optionalUser.get();
            }
        }catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND,username));
        }
        return null;
    }
}
