package com.bym.bankingsystem.repositories;
import com.bym.bankingsystem.models.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT u from User u JOIN u.roles r WHERE u.id = :id AND r.name = :role")
    Optional<User> findByIdAndRole(@Param("id") Long id, @Param("role") String role);
}
