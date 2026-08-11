package pk.edu.niit.library_management_system.Author.Controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pk.edu.niit.library_management_system.Author.Entity.Author;
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

    @GetMapping
    public ResponseEntity<List<Author>> getAll()
    {
            List<Author> authors=authorServices.getAllAuthors();
            if(authors.isEmpty())
            {
                throw new AuthorNotFoundException("GET/author: No authors found");
            }
            log.info("Get/author: All authors found");
            return ResponseEntity.ok(authors);
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author)
    {
            authorServices.createAuthor(author);
            log.info("POST/author: Author created with id {}",author.getAuthorId());
            return ResponseEntity.status(HttpStatus.CREATED).body(author);

    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable long id)
    {

            authorServices.deleteAuthor(id);
            log.info("DELETE/author/id/{}: Author deleted",id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }

    @PutMapping("id/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable long id, @RequestBody Author author)
    {
            Author updated=authorServices.updateAuthor(id,author);
            log.info("PUT/author/id/{} : Author updated",id);
            return ResponseEntity.ok(updated);

    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable long id) {

        Author author = authorServices.getAuthorById(id);
        log.info("GET/author/id/{} :Author found :", id);
        return ResponseEntity.ok(author);
    }

}
