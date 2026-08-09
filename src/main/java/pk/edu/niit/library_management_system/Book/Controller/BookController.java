package pk.edu.niit.library_management_system.Book.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pk.edu.niit.library_management_system.Book.Entity.Book;
import pk.edu.niit.library_management_system.Book.Services.BookServices;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookServices bookServices;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks()
    {
        try{
            List<Book> books=bookServices.getAllBooks();
            log.info("GET/book : {} Books found",books.size());
            return ResponseEntity.ok(books);
        }
        catch (Exception e)
        {
            log.error("Error getting all books: {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book)
    {
        try{
            Book created=bookServices.createBook(book);
            log.info("POST/book : Book created for this id :{}",created.getBookId());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (Exception e)
        {
            log.error("Error creating book: {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id)
    {
        try{
            Book book=bookServices.getBookByID(id);
            if(book==null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book for this id not found");
            }
            bookServices.deleteBook(id);
            log.info("DELETE/book/id/{} : Book deleted for this id : {}",id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (Exception e)
        {
            log.error("Error deleting book for this id {} :",id,e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("id/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book book)
    {
        try {
            Book updateBook = bookServices.updateBook(id, book);
            if (updateBook == null)
            {
                log.info("Book not found for this id: {}",id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            log.info("PUT/book/id/{}: Book updated",id);
            return ResponseEntity.ok(updateBook);
        }
        catch (Exception e)
        {
            log.error("Error updating book : {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id)
    {
        try{
            Book book=bookServices.getBookByID(id);
            if(book==null)
            {
                log.info("Book not found for this id : {}",id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            log.info("GET/id/{}: Book found for this id {}",id);
            return ResponseEntity.status(HttpStatus.FOUND).build();
        }
        catch (Exception e)
        {
            log.error("Error getting book : {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
