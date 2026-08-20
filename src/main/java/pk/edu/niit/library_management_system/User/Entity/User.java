package pk.edu.niit.library_management_system.User.Entity;

import jakarta.persistence.*;
import pk.edu.niit.library_management_system.User.Util.Role;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
