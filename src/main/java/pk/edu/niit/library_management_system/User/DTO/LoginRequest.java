package pk.edu.niit.library_management_system.User.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String password;
}
