package pk.edu.niit.library_management_system.Book.Services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pk.edu.niit.library_management_system.Author.Repository.AuthorRepository;
import pk.edu.niit.library_management_system.Book.Entity.Book;
import pk.edu.niit.library_management_system.Book.Repository.BookRepository;
import pk.edu.niit.library_management_system.ExceptionHandler.BookNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class BookServices {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks()
    {

        return bookRepository.findAll();
    }

    public Book createBook(Book book)
    {
        return bookRepository.save(book);
    }

    public void deleteBook(long id)
    {
        if(!bookRepository.existsById(id))
        {
            throw new BookNotFoundException("DELETE/book/id/"+id+":Book not found for this id "+id);
        }
        bookRepository.deleteById(id);
    }

    public Book updateBook(long id,Book book)
    {
       Book existing=bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(
               "PUT/book/id/"+id+": Book not found for id : "+id));

            existing.setIsbn(book.getIsbn());
            existing.setTitle(book.getTitle());
            existing.setAuthor(book.getAuthor());
            existing.setTotalCopies(book.getTotalCopies());
            existing.setAvailableCopies(book.getAvailableCopies());
            return bookRepository.save(existing);

    }

    public Book getBookByID(long id)
    {
        Book book=bookRepository.findById(id).orElseThrow(()->new BookNotFoundException("PUT/book/id/"+id+": " +
                "Book not found for id : "+id));
        return book;

    }
}
