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
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
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
    private Boolean locked;
    private Boolean enabled;

    @OneToMany(mappedBy = "user")
    private List<AppToken> tokens;

    public User(String username, String password, String name, String identityCard, String phone, String email, String address, String gender, AppUserRole role, Boolean locked, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.identityCard = identityCard;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.role = role;
        this.locked = false;
        this.enabled = false;
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
        return true;
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
