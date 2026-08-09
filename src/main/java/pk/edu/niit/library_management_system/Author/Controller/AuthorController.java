package pk.edu.niit.library_management_system.Author.Controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pk.edu.niit.library_management_system.Author.Entity.Author;
import pk.edu.niit.library_management_system.Author.Services.AuthorServices;

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
        try{
            List<Author> authors=authorServices.getAllAuthors();
            log.info("Get/author: All authors found");
            return ResponseEntity.ok(authors);

        }
        catch (Exception e)
        {
            log.error("Error getting all authors {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author)
    {
        try{
            authorServices.createAuthor(author);
            log.info("POST/ Author created with id {}",author.getAuthorId());
            return ResponseEntity.status(HttpStatus.CREATED).body(author);
        }
        catch (Exception e)
        {
            log.error("Error creating author , {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable long id)
    {
        try{
            Author author=authorServices.getAuthorById(id);
            if (author==null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found for this id");
            }
            authorServices.deleteAuthor(id);
            log.info("DELETE/author/id/{}: Author deleted",id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (Exception e)
        {
            log.error("Error deleting author: {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("id/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable long id, @RequestBody Author author)
    {
        try{
            Author updated=authorServices.updateAuthor(id,author);
            if(updated==null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            log.info("PUT/author/id/{} : Author updated",id);
            return ResponseEntity.ok(updated);
        }
        catch (Exception e)
        {
            log.error("Error updating the author :{}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable long id)
    {
        try{
            Author author=authorServices.getAuthorById(id);
            log.info("GET/author/id/{} :Author found :",id);
            return ResponseEntity.ok(author);
        }
        catch (Exception e)
        {
            log.error("Error getting author with this ID: {} ",id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
