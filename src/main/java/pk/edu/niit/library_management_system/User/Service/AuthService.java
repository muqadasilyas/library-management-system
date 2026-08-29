package pk.edu.niit.library_management_system.User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pk.edu.niit.library_management_system.ExceptionHandler.InvalidCredentialsException;
import pk.edu.niit.library_management_system.ExceptionHandler.UsernameAlreadyExistsException;
import pk.edu.niit.library_management_system.User.DTO.LoginRequest;
import pk.edu.niit.library_management_system.User.DTO.RegisterRequest;
import pk.edu.niit.library_management_system.User.Entity.User;
import pk.edu.niit.library_management_system.User.Repository.UserRepository;
import pk.edu.niit.library_management_system.User.Util.Role;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public void register(RegisterRequest registerRequest)
    {
        if(userRepository.existsByUserName(registerRequest.getUserName()))
        {
            throw new UsernameAlreadyExistsException("Username: "+registerRequest.getUserName()+" already exists");
        }
        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.MEMBER);
        userRepository.save(user);

    }

    public void login(LoginRequest request) throws InvalidCredentialsException
    {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUserName(),
                            request.getPassword()
                    )
            );
        }
        catch(BadCredentialsException e)
        {
            throw new InvalidCredentialsException("Invalid username or password");
        }

    }
}
