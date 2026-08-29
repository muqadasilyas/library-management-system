package pk.edu.niit.library_management_system.User.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Username must not be blank")
    @Size(min = 6,max = 18,message =" Size of username should be between 6 and 18" )
    private String userName;
    @NotBlank(message = "Password must not be blank")
    @Size(min = 6,max = 20,message =" Size of password should be between 6 and 20" )
    private String password;
}
