package com.bym.bankingsystem.models.auth;

import com.bym.bankingsystem.models.Builder;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Role {

    public static class RoleBuilder implements Builder<Role> {
        private Role role;

        public RoleBuilder() {
            this.role = new Role();
        }

        public RoleBuilder withName(String name) {
            this.role.setName(name);
            return this;
        }

        public RoleBuilder withPrivileges(List<Privilege> privileges) {
            this.role.setPrivileges(privileges);
            return this;
        }

        public RoleBuilder withPrivilege(Privilege privilege) {
            this.role.addPrivilege(privilege);
            return this;
        }

        public Role build() {
            return this.role;
        }
    }

    public static RoleBuilder create() {
        return new RoleBuilder();
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @ManyToMany
    @JoinTable(
            name = "privilages_roles",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilage_id", referencedColumnName = "id"
            )
    )
    private List<Privilege> privileges;

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    public void addPrivilege(Privilege privilege) {
        this.getPrivileges().add(privilege);
    }
}
