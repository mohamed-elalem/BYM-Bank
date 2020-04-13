package com.bym.bankingsystem.services;

import com.bym.bankingsystem.models.auth.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByIdAndRole(Long id, String role);
    List<User> findAllByRole(String role);
    User createEnabledUser(User user, String roleName);
}
