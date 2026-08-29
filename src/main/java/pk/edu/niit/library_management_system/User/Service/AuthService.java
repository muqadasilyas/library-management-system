package pk.edu.niit.library_management_system.User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pk.edu.niit.library_management_system.ExceptionHandler.UsernameAlreadyExistsException;
import pk.edu.niit.library_management_system.User.DTO.RegisterRequest;
import pk.edu.niit.library_management_system.User.Entity.User;
import pk.edu.niit.library_management_system.User.Repository.UserRepository;
import pk.edu.niit.library_management_system.User.Util.Role;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void register(RegisterRequest registerRequest)
    {
        if(userRepository.existsByUsername(registerRequest.getUserName()))
        {
            throw new UsernameAlreadyExistsException("Username: "+registerRequest.getUserName()+" already exists");
        }
        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.MEMBER);
        userRepository.save(user);

    }
}
