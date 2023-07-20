package com.dansmultipro.test.jobwebservices.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements LoginAbleUser, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "password", length = 500)
    private String password;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @Column(name = "creation_time", updatable = false)
    private LocalDateTime creationTime;

    @Column(name = "created_by", length = 36)
    private String createdBy;

    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    @Column(name = "last_modified_by", length = 36)
    private String lastModifiedBy;

    @PrePersist
    public void prePersist() {
        this.creationTime = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastModified = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
