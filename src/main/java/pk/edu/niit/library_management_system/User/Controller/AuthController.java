package pk.edu.niit.library_management_system.User.Controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pk.edu.niit.library_management_system.User.DTO.RegisterRequest;
import pk.edu.niit.library_management_system.User.Service.AuthService;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest)
    {
        authService.register(registerRequest);
        log.info("POST/auth/register: User registered");
        return ResponseEntity.ok("User registered successfully");

    }
}
