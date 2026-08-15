package pk.edu.niit.library_management_system.Member.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberRequestDTO {
    @Size(min=10, max=20, message = "Member name should be between 10 and 20")
    private String memberName;
    @Email(message = "It should be written in email format")
    private String email;
    @NotNull(message = "Membership date must not be null")
    private LocalDate membershipDate;
}
