package pk.edu.niit.library_management_system.Author.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthorResponseDTO {
    private long Id;
    private String authorName;
    private String bio;
}
