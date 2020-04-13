package com.bym.bankingsystem.models.auth;

import com.bym.bankingsystem.models.Builder;
import com.bym.bankingsystem.models.BuilderCreator;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Privilege {

    public static class PrivilegeBuilder implements Builder<Privilege> {
        private Privilege privilege;

        public PrivilegeBuilder() {
            this.privilege = new Privilege();
        }

        public PrivilegeBuilder withName(String name) {
            this.privilege.setName(name);
            return this;
        }

        public PrivilegeBuilder withRole(Role role) {
            this.privilege.addRole(role);
            return this;
        }

        public Privilege build() {
            return this.privilege;
        }
    }
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String name;

    @JsonBackReference
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.getRoles().add(role);
    }

    public static PrivilegeBuilder create() {
        return new PrivilegeBuilder();
    }
}
