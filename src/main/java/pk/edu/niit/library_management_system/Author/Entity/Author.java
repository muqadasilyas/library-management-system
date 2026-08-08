package pk.edu.niit.library_management_system.Author.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Author")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long authorId;
    @Column
    private String authorName;
    @Column
    private String bio;

}
