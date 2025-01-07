package com.example.demo.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tbl_users")
@Getter@ToString
@AllArgsConstructor@NoArgsConstructor
@Builder
@EntityListeners(value = {AuditingEntityListener.class})
public class UserEntity implements UserDetails {
    @Id//id는 이메일로 합니다.
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "pw", nullable = false)
    private String pw;

    @CreatedDate
    private LocalDateTime joinDate;

    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

}
