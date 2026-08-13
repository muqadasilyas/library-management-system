package pk.edu.niit.library_management_system.Member.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name="Member")
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;
    @Column
    @Size(min=10, max=20, message = "Member name should be between 10 and 20")
    private String memberName;
    @Column
    @Email(message = "It should be written in email format")
    private String email;
    @Column
    private LocalDate membershipDate;
}
