package com.ticket.server.service;


import com.ticket.server.model.User;
import com.ticket.server.model.UserRegister;
import com.ticket.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<?> changePassword(String username, String newPassword) {
        final Optional<User> user = _userRepository.findByUsername(username);

        if (user.isPresent()){
            final User oldUser = user.get();
            oldUser.setPassword(newPassword);
            _userRepository.save(oldUser);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.badRequest().body("Can not find any user have username match with yours");

    }
}
