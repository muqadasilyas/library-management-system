package pk.edu.niit.library_management_system.Author.Controller;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pk.edu.niit.library_management_system.Author.DTO.AuthorRequestDTO;
import pk.edu.niit.library_management_system.Author.DTO.AuthorResponseDTO;
import pk.edu.niit.library_management_system.Author.Entity.Author;
import pk.edu.niit.library_management_system.Author.Mapper.AuthorMapper;
import pk.edu.niit.library_management_system.Author.Services.AuthorServices;
import pk.edu.niit.library_management_system.ExceptionHandler.AuthorNotFoundException;

import java.util.List;

@Slf4j
@RestController
@Data
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorServices authorServices;
    @Autowired
    private AuthorMapper authorMapper;

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAll()
    {
            List<Author> authors=authorServices.getAllAuthors();

            if(authors.isEmpty())
            {
                throw new AuthorNotFoundException("GET/author: No authors found");
            }
            List<AuthorResponseDTO> responseDTOS=authors.stream().map(author ->
            {AuthorResponseDTO response=authorMapper.toResponseDTO(author);
                return response;}).toList();
            log.info("Get/author: All authors found");
            return ResponseEntity.ok(responseDTOS);
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(@Valid @RequestBody AuthorRequestDTO dto)
    {
            Author author= authorMapper.toEntity(dto);
            Author created=authorServices.createAuthor(author);
            AuthorResponseDTO responseDTO = authorMapper.toResponseDTO(created);
            log.info("POST/author: Author created with id {}",responseDTO.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable long id)
    {

            authorServices.deleteAuthor(id);
            log.info("DELETE/author/id/{}: Author deleted",id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }

    @PutMapping("id/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable long id,@Valid @RequestBody AuthorRequestDTO authorRequestDTO)
    {
           Author author=authorMapper.toEntity(authorRequestDTO);
            Author updated=authorServices.updateAuthor(id,author);
            AuthorResponseDTO responseDTO=authorMapper.toResponseDTO(updated);
            log.info("PUT/author/id/{} : Author updated",id);
            return ResponseEntity.ok(responseDTO);

    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable long id) {

        Author author = authorServices.getAuthorById(id);
        AuthorResponseDTO responseDTO=authorMapper.toResponseDTO(author);
        log.info("GET/author/id/{} :Author found :", id);
        return ResponseEntity.ok(responseDTO);
    }

}
