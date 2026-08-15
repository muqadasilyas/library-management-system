package pk.edu.niit.library_management_system.Member.DTO;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberResponseDTO {
    private long memberId;
    private String memberName;
    private String email;
    private LocalDate membershipDate;
}
