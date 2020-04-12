package com.bym.bankingsystem.models.auth;

import com.bym.bankingsystem.models.Builder;
import com.bym.bankingsystem.models.BuilderCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Transient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User implements UserDetails {

    public static class UserBuilder implements Builder<User> {
        private User user;

        public UserBuilder() {
            this.user = new User();
        }

        public UserBuilder withUsername(String username) {
            this.user.setUsername(username);
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.user.setEmail(email);
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.user.setPassword(password);
            return this;
        }

        public UserBuilder withEnabled(boolean enabled) {
            this.user.setEnabled(enabled);
            return this;
        }

        public UserBuilder withRole(Role role) {
            this.user.addRole(role);
            return this;
        }

        public UserBuilder withRoles(List<Role> roles) {
            this.user.setRoles(roles);
            return this;
        }

        @Override
        public User build() {
            return this.user;
        }
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private boolean enabled;

    @ManyToMany
    @JoinTable(
            name = "roles_users",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                name = "role_id", referencedColumnName = "id"
            )
    )
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        if (this.getRoles() == null) {
            this.setRoles(new ArrayList<>());
        }
        this.getRoles().add(role);
    }

    public static UserBuilder create() {
        return new UserBuilder();
    }
}
