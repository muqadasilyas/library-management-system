package pk.edu.niit.library_management_system.Author.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthorRequestDTO {
    @NotBlank(message = "Name must not be blank")
    private String authorName;
    @Size(min=10,max=500,message = "Bio description must be between 10 and 50 characters")
    private String bio;
}
