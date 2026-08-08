package pk.edu.niit.library_management_system.Member.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="Member")
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;
    @Column
    private String memberName;
    @Column
    private String email;
    @Column
    private LocalDate membershipDate;
}
