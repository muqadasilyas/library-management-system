package pk.edu.niit.library_management_system.Book.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pk.edu.niit.library_management_system.Author.Entity.Author;
import pk.edu.niit.library_management_system.Author.Services.AuthorServices;
import pk.edu.niit.library_management_system.Book.DTO.BookRequestDTO;
import pk.edu.niit.library_management_system.Book.DTO.BookResponseDTO;
import pk.edu.niit.library_management_system.Book.Entity.Book;

@Component
public class BookMapper {
    @Autowired
    AuthorServices authorServices;
    public Book toEntity(BookRequestDTO requestDTO)
    {
         Book book=new Book();
        Author author=authorServices.getAuthorById(requestDTO.getAuthorId());
        book.setIsbn(requestDTO.getIsbn());
        book.setTitle(requestDTO.getTitle());
        book.setAuthor(author);
        book.setTotalCopies(requestDTO.getTotalCopies());
        book.setAvailableCopies(requestDTO.getAvailableCopies());
       return book;
    }

    public BookResponseDTO toResponseDTO(Book book)
    {
        BookResponseDTO responseDTO=new BookResponseDTO();
        responseDTO.setBookId(book.getBookId());
        responseDTO.setIsbn(book.getIsbn());
        responseDTO.setAuthorName(book.getAuthor().getAuthorName());
        responseDTO.setTitle(book.getTitle());
        responseDTO.setTotalCopies(book.getTotalCopies());
        responseDTO.setAvailableCopies(book.getAvailableCopies());
    }

}
