package com.bym.bankingsystem.bootstrap;

import com.bym.bankingsystem.models.auth.Privilege;
import com.bym.bankingsystem.models.auth.Role;
import com.bym.bankingsystem.models.auth.User;
import com.bym.bankingsystem.repositories.PrivilegeRepository;
import com.bym.bankingsystem.repositories.RoleRepository;
import com.bym.bankingsystem.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class AdminBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PrivilegeRepository privilegeRepository;

    private PasswordEncoder passwordEncoder;

    public AdminBootstrap(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PrivilegeRepository privilegeRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

//        if (alreadySetup)
//            return;
//        Privilege readPrivilege
//                = createPrivilegeIfNotFound("READ_PRIVILEGE");
//        Privilege writePrivilege
//                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
//
//        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
//        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
//        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
//
//        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
//        User user = User.create()
//                .withEmail("test@test.com")
//                .withPassword(this.passwordEncoder.encode("test"))
//                .withRoles(Arrays.asList(adminRole))
//                .withUsername("admin")
//                .withEnabled(true)
//                .build();
//
//        userRepository.save(user);
//
//        alreadySetup = true;
    }

    @Transactional
    public Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = Privilege.create().withName(name).build();
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    public Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = Role.create().withName(name).withPrivileges(privileges).build();
            roleRepository.save(role);
        }
        return role;
    }
}
