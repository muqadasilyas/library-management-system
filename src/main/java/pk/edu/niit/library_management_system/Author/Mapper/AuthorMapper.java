package pk.edu.niit.library_management_system.Author.Mapper;

import org.springframework.stereotype.Component;
import pk.edu.niit.library_management_system.Author.DTO.AuthorRequestDTO;
import pk.edu.niit.library_management_system.Author.DTO.AuthorResponseDTO;
import pk.edu.niit.library_management_system.Author.Entity.Author;

@Component
public class AuthorMapper {
    public Author toEntity(AuthorRequestDTO dto)
    {
        Author author=new Author();
        author.setAuthorName(dto.getAuthorName());
        author.setBio(dto.getBio());
        return author;
    }
    public AuthorResponseDTO toResponseDTO(Author author)
    {
        AuthorResponseDTO responseDTO=new AuthorResponseDTO();
        responseDTO.setId(author.getAuthorId());
        responseDTO.setAuthorName(author.getAuthorName());
        responseDTO.setBio(author.getBio());
        return responseDTO;
    }
}
