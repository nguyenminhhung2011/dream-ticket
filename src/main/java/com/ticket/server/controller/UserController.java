package com.ticket.server.controller;

import com.ticket.server.config.JwtService;
import com.ticket.server.dtos.UserDto.UserDto;
import com.ticket.server.model.RegisterRequest;
import com.ticket.server.model.User;
import com.ticket.server.model.UserRegister;
import com.ticket.server.repository.UserRepository;
import com.ticket.server.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/get")
    ResponseEntity<?> getUser(
            @RequestParam String token
    ){
        try {
            System.out.println(token);
            final String username = jwtService.extractUsername(token);
                final Optional<User> optionalUser = userRepository.findByUsername(username);
                if (optionalUser.isPresent()) {
                    return ResponseEntity.ok(UserDto.fromEntity(optionalUser.get()));
                }
            return ResponseEntity.badRequest().body("Can not find any user");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Something wrong happened: "+ e.getMessage());
        }
    }
    @PutMapping("/changePassword")
    ResponseEntity<?> changePassword(
            @RequestParam String token,
            @RequestParam String oldPassword,
            @RequestParam String  newPassword
            ){
       try {
           final String username = jwtService.extractUsername(token);

           final Optional<User> optionalUser = userRepository.findByUsername(username);

           if (optionalUser.isPresent()){
               final User data = optionalUser.get();
               if (passwordEncoder.matches(oldPassword,data.getPassword())){
                   data.setPassword(passwordEncoder.encode(newPassword));

                   final User newUserEntity = userRepository.save(data);

                   return ResponseEntity.ok(UserDto.fromEntity(newUserEntity));
               }
               return ResponseEntity.badRequest().body("Password is incorrect");
           }
           return ResponseEntity.badRequest().body("Something wrong happened");
       }catch (Exception e){
           return ResponseEntity.badRequest().body("Something wrong happened: "+ e.getMessage());
       }
    }

    @PutMapping("/update")
    ResponseEntity<?> updateUser(
            @RequestParam String token,
            @RequestBody UserDto user
    ){
        try {
            final String username = jwtService.extractUsername(token);

            final Optional<User> optionalUser = userRepository.findByUsername(username);
            if (optionalUser.isPresent()){
                if (username.equals(user.getUsername())) {
                    User newUser = User
                            .builder()
                            .id(user.getId())
                            .password(user.getPassword())
                            .address(user.getAddress())
                            .gender(user.getGender())
                            .email(user.getEmail())
                            .name(user.getName())
                            .identityCard(user.getIdentityCard())
                            .role(user.getRole())
                            .phone(user.getPhone())
                            .enabled(true)
                            .username(user.getUsername())
                            .build();

                    final User newUserEntity = userRepository.save(newUser);
                    return ResponseEntity.ok(UserDto.fromEntity(newUserEntity));
                }
            }
            return ResponseEntity.badRequest().body("Something wrong happened");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Something wrong happened: "+ e.getMessage());
        }
    }
}
