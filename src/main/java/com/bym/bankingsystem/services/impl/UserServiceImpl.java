package com.bym.bankingsystem.services.impl;

import com.bym.bankingsystem.models.auth.Role;
import com.bym.bankingsystem.models.auth.User;
import com.bym.bankingsystem.repositories.RoleRepository;
import com.bym.bankingsystem.repositories.UserRepository;
import com.bym.bankingsystem.services.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RabbitTemplate rabbitTemplate;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           RabbitTemplate rabbitTemplate) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<User> findAllByRole(String name) {
        Role role = roleRepository.findOneByName(name);
        List<User> users = role.getUsers();
        return users;
    }

    @Override
    public User createEnabledUser(User user, String roleName) {
        user.setRawPassword(user.getPassword());
        user.setEnabled(true);
        user.addRole(roleRepository.findOneByName(roleName));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        notifyUserCreation(user, roleName);
        return user;
    }

    @Override
    public Optional<User> findByIdAndRole(Long id, String role) {
        return this.userRepository.findByIdAndRole(id, role);
    }

    private void notifyUserCreation(User user, String role) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
        User loggedInUser = userRepository.findByUsername(loggedInUsername);

        HashMap<String, String> message = new HashMap<>() {{
           put("createdUserId", user.getId().toString());
           put("creatingUserId", loggedInUser.getId().toString());
           put("password", user.getRawPassword());
        }};

        System.out.println(message instanceof Serializable);
        rabbitTemplate.convertAndSend("tellerCreation", "", message);
    }
}
