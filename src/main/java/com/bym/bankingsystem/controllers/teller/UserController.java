package com.bym.bankingsystem.controllers.teller;

import com.bym.bankingsystem.models.auth.User;
import com.bym.bankingsystem.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping({"/api/teller/users"})
public class UserController extends BaseTellerController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<?> indexAction() {
        return ResponseEntity.ok(userService.findAllByRole("ROLE_USER"));
    }

    @PostMapping("")
    @ApiOperation(value = "Count the number of entities associated with resource name. This operation does not requires any role." , authorizations = {@Authorization(value = "Bearer")})
    public ResponseEntity<?> createAction(@RequestBody User teller) {
        teller = userService.createEnabledUser(teller, "ROLE_USER");
        return ResponseEntity.ok(teller);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> showAction(@PathVariable("id") Long id) {
        Optional<User> user = userService.findByIdAndRole(id, "ROLE_USER");
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user.get());
    }
}
