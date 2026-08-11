package pk.edu.niit.library_management_system.Book.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pk.edu.niit.library_management_system.Book.Entity.Book;
import pk.edu.niit.library_management_system.Book.Services.BookServices;
import pk.edu.niit.library_management_system.ExceptionHandler.BookNotFoundException;

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
            List<Book> books=bookServices.getAllBooks();
            if (books.isEmpty())
            {
                throw new BookNotFoundException("GET/books:Books not found");
            }
            log.info("GET/book : {} Books found",books.size());
            return ResponseEntity.ok(books);


    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book)
    {
            Book created=bookServices.createBook(book);
            log.info("POST/book : Book created for this id :{}",created.getBookId());
            return ResponseEntity.status(HttpStatus.CREATED).body(created);


    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id)
    {

            bookServices.deleteBook(id);
            log.info("DELETE/book/id/{} : Book deleted for this id : {}",id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("id/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book book)
    {
            Book updateBook = bookServices.updateBook(id, book);
            log.info("PUT/book/id/{}: Book updated",id);
            return ResponseEntity.ok(updateBook);

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
            return ResponseEntity.status(HttpStatus.FOUND).body(book);
        }
        catch (Exception e)
        {
            log.error("Error getting book : {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
