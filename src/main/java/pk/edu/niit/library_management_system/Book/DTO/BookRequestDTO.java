package pk.edu.niit.library_management_system.Book.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pk.edu.niit.library_management_system.Author.Entity.Author;

@Data
public class BookRequestDTO {
    @NotBlank
    @Size(min=10,max=50,message = "Title size should be between 10 and 50")
    private String title;
    @NotBlank(message = "ISBN must be valid")
    private String isbn;
    @Min(value=1,message = "Total copies must not be less than 1")
    private int totalCopies;
    @Min(value=0,message = "Available copies must not be less than 0")
    private int availableCopies;
    private Author author;
}
