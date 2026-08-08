package pk.edu.niit.library_management_system.Book.Entity;

import jakarta.persistence.*;
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
    private String title;
    @Column
    private String isbn;
    @Column
    private int totalCopies;
    @Column
    private int availableCopies;
    @ManyToOne
    @JoinColumn(name="authorId")
    private Author author;

}
