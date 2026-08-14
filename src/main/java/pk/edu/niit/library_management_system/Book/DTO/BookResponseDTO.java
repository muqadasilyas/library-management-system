package pk.edu.niit.library_management_system.Book.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import pk.edu.niit.library_management_system.Author.Entity.Author;

@Data
public class BookResponseDTO {
    private long bookId;
    private String title;
    private String isbn;
    private int totalCopies;
    private int availableCopies;
    private String AuthorName;
}
