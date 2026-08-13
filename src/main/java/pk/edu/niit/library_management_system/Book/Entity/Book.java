package pk.edu.niit.library_management_system.Book.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pk.edu.niit.library_management_system.Author.Entity.Author;

@Entity
@Table(name="Book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;
    @Column
    @NotBlank
    @Size(min=10,max=50,message = "Title size should be between 10 and 50")
    private String title;
    @Column
    @NotBlank(message = "ISBN must be valid")
    private String isbn;
    @Column
    @Min(value=1,message = "Total copies must not be less than 1")
    private int totalCopies;
    @Column
    @Min(value=0,message = "Available copies must not be less than 0")
    private int availableCopies;
    @ManyToOne
    @JoinColumn(name="authorId")
    private Author author;

}
