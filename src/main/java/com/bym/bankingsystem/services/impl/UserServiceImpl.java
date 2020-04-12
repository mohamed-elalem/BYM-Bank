package com.bym.bankingsystem.services.impl;

import com.bym.bankingsystem.models.auth.Role;
import com.bym.bankingsystem.models.auth.User;
import com.bym.bankingsystem.repositories.RoleRepository;
import com.bym.bankingsystem.repositories.UserRepository;
import com.bym.bankingsystem.services.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAllByRole(String name) {
        Role role = roleRepository.findOneByName(name);
        List<User> users = role.getUsers();
        return users;
    }

    @Override
    public User createEnabledUser(User user, String roleName) {
        user.setEnabled(true);
        user.addRole(roleRepository.findOneByName(roleName));
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> findByIdAndRole(Long id, String role) {
        return this.userRepository.findByIdAndRole(id, role);
    }
}
