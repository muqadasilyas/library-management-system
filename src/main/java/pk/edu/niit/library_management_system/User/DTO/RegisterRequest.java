package pk.edu.niit.library_management_system.User.DTO;

import lombok.Data;

@Data
public class RegisterRequest {
    private String userName;
    private String password;
}
