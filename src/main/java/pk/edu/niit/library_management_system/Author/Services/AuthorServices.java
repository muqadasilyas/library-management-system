package pk.edu.niit.library_management_system.Author.Services;

import ch.qos.logback.classic.spi.IThrowableProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pk.edu.niit.library_management_system.Author.Entity.Author;
import pk.edu.niit.library_management_system.Author.Repository.AuthorRepository;
import pk.edu.niit.library_management_system.ExceptionHandler.AuthorNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class AuthorServices {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors()
    {
        return authorRepository.findAll();

    }

    public Author createAuthor(Author author)
    {

        return authorRepository.save(author);
    }


    public void deleteAuthor(long id)
    {
        if(!authorRepository.existsById(id))
        {
            throw new AuthorNotFoundException(
                    "DELETE/author/id/{ "+id +"} :Author with this id " + id + "not found"
            );
        }
        authorRepository.deleteById(id);
    }

    public Author updateAuthor(long id,Author author)
    {
        Author existingAuthor=authorRepository.findById(id).orElseThrow(()-> new AuthorNotFoundException("PUT/author/id/: Author not found for this id: "+id));

            existingAuthor.setAuthorName(author.getAuthorName());
            existingAuthor.setBio(author.getBio());
            return authorRepository.save(existingAuthor);


    }

    public Author getAuthorById(long id)
    {
        return authorRepository.findById(id).orElseThrow(()->
       new AuthorNotFoundException("Author Not Found with this " + id));

    }
}
