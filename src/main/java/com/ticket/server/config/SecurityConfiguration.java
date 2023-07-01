package com.ticket.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticket.server.model.AuthenticationResponse;
import com.ticket.server.service.LogoutService;
import jakarta.servlet.Filter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthentication jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutService logoutService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        try {
            return http
                    .cors()
                    .and()
                    .csrf()
                    .disable()
                    .authorizeHttpRequests()
                    .requestMatchers(
                            "/api/v1/auth/**"
                            )
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                    .logout()
                    .logoutUrl("/api/v1/auth/logout")
                    .addLogoutHandler(logoutService)
                    .logoutSuccessHandler((request, response, authentication) -> {

                        final String authenticationJson = new ObjectMapper().writeValueAsString(AuthenticationResponse
                                                        .builder()
                                                        .message("Logout Successfully")
                                                        .isSuccess(true)
                                                        .build());

                        response.setStatus(HttpStatus.OK.value());
                        response.setContentType("application/json"  );
                        response.setCharacterEncoding("UTF-8");

                        response.getWriter().print(authenticationJson);

                        response.flushBuffer();

                        SecurityContextHolder.clearContext();
                    })
                    .and()
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        System.out.println("corsConfigurationSource");
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
