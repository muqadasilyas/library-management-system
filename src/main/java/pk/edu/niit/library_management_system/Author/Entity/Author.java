package pk.edu.niit.library_management_system.Author.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pk.edu.niit.library_management_system.Book.Entity.Book;
import java.util.List;

@Entity
@Table(name = "Author")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long authorId;
    @Column
    @NotBlank(message = "Author name is required")
    private String authorName;
    @Column
    @Size(min=10,max=500, message = "bio description must be between 10 and 500")
    private String bio;
    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Book> books;

}
