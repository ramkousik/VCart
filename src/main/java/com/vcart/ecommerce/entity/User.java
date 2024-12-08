package com.vcart.ecommerce.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User implements UserDetails {
    @Id
    @Getter @Setter
    private String id;

    @NotBlank(message = "First name is mandatory")
    @Getter @Setter
    private String firstname;

    @NotBlank(message = "Last name is mandatory")
    @Getter @Setter
    private String lastname;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is mandatory")
    @Getter @Setter
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @Getter @Setter
    private Date createdAt;

    @Getter @Setter
    private Date updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }
}