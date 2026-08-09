package pk.edu.niit.library_management_system.Book.Services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pk.edu.niit.library_management_system.Author.Repository.AuthorRepository;
import pk.edu.niit.library_management_system.Book.Entity.Book;
import pk.edu.niit.library_management_system.Book.Repository.BookRepository;

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
        bookRepository.deleteById(id);
    }

    public Book updateBook(long id,Book book)
    {
        Optional<Book> existing=bookRepository.findById(id);
        if(existing.isPresent())
        {
            Book updated=existing.get();
            updated.setIsbn(book.getIsbn());
            updated.setTitle(book.getTitle());
            updated.setAuthor(book.getAuthor());
            updated.setTotalCopies(book.getTotalCopies());
            updated.setAvailableCopies(book.getAvailableCopies());
            return bookRepository.save(updated);
        }
        return null;
    }

    public Book getBookByID(long id)
    {
        Optional<Book> book=bookRepository.findById(id);
        if (book.isPresent())
        {
            Book bookFound=book.get();
            return bookFound;
        }
        return null;

    }
}
