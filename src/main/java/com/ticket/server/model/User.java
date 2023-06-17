package com.ticket.server.model;

import com.ticket.server.enums.AppUserRole;
import com.ticket.server.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id

    @SequenceGenerator(
        name = "user_sequence",
        sequenceName = "user_sequence",
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.UUID,
        generator = "user_sequence"
    )
    private UUID id;
    private String username;
    private String password;
    private String fullName;
    private String identityCard;
    private String phone;
    private String email;
    private String address;
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private AppUserRole role;
    private Boolean locked;
    private Boolean enabled;

    public User(String username, String password, String fullName, String identityCard, String phone, String email, String address, Gender gender, AppUserRole role, Boolean locked, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.identityCard = identityCard;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.role = role;
        this.locked = locked;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =  new SimpleGrantedAuthority(role.name());

        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


}
