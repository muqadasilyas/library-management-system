package pk.edu.niit.library_management_system.Book.Controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pk.edu.niit.library_management_system.Book.DTO.BookRequestDTO;
import pk.edu.niit.library_management_system.Book.DTO.BookResponseDTO;
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
    public ResponseEntity<List<BookResponseDTO>> getAllBooks()
    {
            List<Book> books=bookServices.getAllBooks();
            if (books.isEmpty())
            {
                throw new BookNotFoundException("GET/books:Books not found");
            }
            List<BookResponseDTO> responseDTOS=books.stream().map(book->{
                BookResponseDTO responseDTO=new BookResponseDTO();
                responseDTO.setBookId(book.getBookId());
                responseDTO.setIsbn(book.getIsbn());
                responseDTO.setAuthor(book.getAuthor());
                responseDTO.setTitle(book.getTitle());
                responseDTO.setTotalCopies(book.getTotalCopies());
                responseDTO.setAvailableCopies(book.getAvailableCopies());
                return responseDTO;
            }).toList();
            log.info("GET/book : {} Books found",responseDTOS.size());
            return ResponseEntity.ok(responseDTOS);


    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody BookRequestDTO dto)
    {
            Book book=new Book();
            book.setIsbn(dto.getIsbn());
            book.setTitle(dto.getTitle());
            book.setAuthor(dto.getAuthor());
            book.setTotalCopies(dto.getTotalCopies());
            book.setAvailableCopies(dto.getAvailableCopies());
            Book created=bookServices.createBook(book);
            BookResponseDTO responseDTO=new BookResponseDTO();
            responseDTO.setBookId(book.getBookId());
            responseDTO.setIsbn(book.getIsbn());
            responseDTO.setAuthor(book.getAuthor());
            responseDTO.setTitle(book.getTitle());
            responseDTO.setTotalCopies(book.getTotalCopies());
            responseDTO.setAvailableCopies(book.getAvailableCopies());
            log.info("POST/book : Book created for this id :{}",responseDTO.getBookId());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);


    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id)
    {

            bookServices.deleteBook(id);
            log.info("DELETE/book/id/{} : Book deleted for this id : {}",id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("id/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable long id, @Valid @RequestBody BookRequestDTO dto)
    {
            Book book=new Book();
            book.setAvailableCopies(dto.getAvailableCopies());
            book.setIsbn(dto.getIsbn());
            book.setTitle(dto.getTitle());
            book.setAuthor(dto.getAuthor());
            book.setTotalCopies(dto.getTotalCopies());
            Book updateBook = bookServices.updateBook(id, book);
            BookResponseDTO responseDTO=new BookResponseDTO();
            responseDTO.setBookId(book.getBookId());
            responseDTO.setIsbn(book.getIsbn());
            responseDTO.setAuthor(book.getAuthor());
            responseDTO.setTitle(book.getTitle());
            responseDTO.setTotalCopies(book.getTotalCopies());
            responseDTO.setAvailableCopies(book.getAvailableCopies());
            log.info("PUT/book/id/{}: Book updated",id);
            return ResponseEntity.ok(responseDTO);

    }

    @GetMapping("id/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable long id)
    {
            Book book=bookServices.getBookByID(id);
            BookResponseDTO responseDTO=new BookResponseDTO();
            responseDTO.setBookId(book.getBookId());
            responseDTO.setIsbn(book.getIsbn());
            responseDTO.setAuthor(book.getAuthor());
            responseDTO.setTitle(book.getTitle());
            responseDTO.setTotalCopies(book.getTotalCopies());
            responseDTO.setAvailableCopies(book.getAvailableCopies());

            log.info("GET/id/{}: Book found for this id {}",id);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

    }

}
g