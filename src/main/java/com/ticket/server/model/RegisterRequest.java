package com.ticket.server.model;

import com.ticket.server.enums.AppUserRole;
import com.ticket.server.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@Data
public class RegisterRequest {
    private final String username;
    private final String password;
    private final String name;
    private final String identityCard;
    private final String phone;
    private final String email;
    private final String address;
    private final String gender;
    private final long birthday;

    @Enumerated(EnumType.STRING)
    private final AppUserRole role;

}
