package com.ticket.server.service.ServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticket.server.config.JwtService;
import com.ticket.server.model.*;
import com.ticket.server.repository.AppTokenRepository;
import com.ticket.server.repository.ConfirmationTokenRepository;
import com.ticket.server.repository.UserRepository;
import com.ticket.server.service.EmailService;
import com.ticket.server.service.IService.IAuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class    AuthenticationServiceImpl implements IAuthenticationService {

    private final JwtService _jwtService;
    private final UserRepository _userRepository;
    private final PasswordEncoder _passwordEncoder;
    private final ConfirmationTokenRepository _confirmationTokenRepository;
    private final AuthenticationManager _authenticationManager;
    private final EmailService _emailService;
    private final AppTokenRepository appTokenRepository;


    @Override
    public ResponseEntity<?> register(RegisterRequest request) {

        if (_userRepository.existsByUsername(request.getUsername())){
            return ResponseEntity.badRequest().body("A user with that username already exists !!! Try another one");
        }

        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .address(request.getUsername())
                .gender(request.getGender())
                .identityCard(request.getIdentityCard())
                .role(request.getRole())
                .birthday(request.getBirthday())
                .enabled(true)
                .phone(request.getPhone())
                .username(request.getUsername())
                .password(_passwordEncoder.encode(request.getPassword())).build();

        User savedUser = _userRepository.save(user);
        final AppToken jwtToken = _jwtService.generateAccessToken(savedUser).user(savedUser).build();

        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .token(jwtToken.getToken())
                .createdAt(new Date(System.currentTimeMillis()))
                .expiredAt(jwtToken.getExpiredTime())
                .user(user)
                .build();

        _confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("[Flight Booking] Verify your account now !!!");
        simpleMailMessage.setText("To confirm your account, please click here\n" +
                "http://localhost:8080/api/v1/auth/confirm_email?token="+confirmationToken.getToken());

        _emailService.sendMail(simpleMailMessage);

        return ResponseEntity.ok("Verify account link sent to your email");
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        _authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));

        Optional<User> user = _userRepository.findByUsername(request.getUsername());

        if (user.isPresent()){
            User _user = user.get();

            AppToken jwtToken = _jwtService.generateAccessToken(_user).user(_user).build();
            final AppToken refreshToken = _jwtService.generateRefreshToken(_user).user(_user).build();

            //save access token
            appTokenRepository.save(jwtToken);

            return AuthenticationResponse.builder()
                    .message("Login Successfully")
                    .isSuccess(true)
                    .refreshToken(refreshToken.getToken())
                    .accessToken(jwtToken.getToken())
                    .expiredTime(jwtToken.getExpiredTime().getTime())
                    .role(_user.getRole())
                    .build();
        }

        return AuthenticationResponse.builder().message("Username or Password is incorrect ").isSuccess(false).build();
    }

    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        Optional<ConfirmationToken> optionalConfirmationToken = _confirmationTokenRepository.findByToken(confirmationToken);
        if (optionalConfirmationToken.isPresent()){
            final ConfirmationToken token = optionalConfirmationToken.get();
            var user = token.getUser();
            if (user!=null){
                user.setEnabled(true);
                token.setConfirmAt(new Date(System.currentTimeMillis()));
                _userRepository.save(user);
                return ResponseEntity.ok("Email verified successfully !");
            }
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }

    private void revokeAllUserToken(User user){
        var validUserToken = appTokenRepository.findALlValidTokenByUser(user.getId());

        if (validUserToken.isEmpty()) return;

        validUserToken.forEach(appToken -> {
            appToken.setRevoke(true);
            appToken.setExpired(true);
        });

        appTokenRepository.saveAll(validUserToken);
    }

    @Override
    public ResponseEntity<?> refreshToken(String refreshToken) {
        final String username = _jwtService.extractUsername(refreshToken);

        if (username != null){
            var user = _userRepository.findByUsername(username).orElseThrow();

            if (_jwtService.isTokenValid(refreshToken,user)){
                revokeAllUserToken(user);

                final AppToken accessToken= _jwtService.generateAccessToken(user).build();

                appTokenRepository.save(accessToken);

                return ResponseEntity.ok(AuthenticationResponse
                        .builder()
                        .accessToken(accessToken.getToken())
                        .refreshToken(refreshToken)
                        .expiredTime(accessToken.getExpiredTime().getTime())
                        .isSuccess(true)
                        .build());
            }
        }
            return ResponseEntity.badRequest().body(AuthenticationResponse
                    .builder()
                    .message("Can not found valid user ")
                    .isSuccess(true)
                    .build());
    }

    @Override
    public ResponseEntity<?> changePassword(String username,String newPassword) {
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

