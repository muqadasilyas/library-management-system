package pk.edu.niit.library_management_system.User.Entity;

import jakarta.persistence.*;
import lombok.Data;
import pk.edu.niit.library_management_system.User.Util.Role;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,unique=true)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
