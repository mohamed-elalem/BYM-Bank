package com.bym.bankingsystem.controllers.admin;

import com.bym.bankingsystem.models.auth.Role;
import com.bym.bankingsystem.models.auth.User;
import com.bym.bankingsystem.repositories.RoleRepository;
import com.bym.bankingsystem.repositories.UserRepository;
import io.swagger.models.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/tellers"})
public class TellerController extends BaseAdminController {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public TellerController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> indexAction() {
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/")
    public ResponseEntity<?> createAction(@RequestBody User teller) {
        teller.addRole(this.roleRepository.findOneByName("ROLE_USER"));
        teller.setEnabled(true);

        userRepository.save(teller);

        return ResponseEntity.ok(teller);
    }
}
