package com.ticket.server.dtos.UserDto;

import com.ticket.server.enums.AppUserRole;
import com.ticket.server.model.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String identityCard;
    private String phone;
    private String email;
    private String address;
    private String gender;
    private long birthday;

    @Enumerated(EnumType.STRING)
    private AppUserRole role;

    public static UserDto fromEntity(User user){
        return UserDto
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
                .username(user.getUsername())
                .birthday(user.getBirthday())
                .build();
    }
}
