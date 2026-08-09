package pk.edu.niit.library_management_system.Author.Services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pk.edu.niit.library_management_system.Author.Entity.Author;
import pk.edu.niit.library_management_system.Author.Repository.AuthorRepository;

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
        authorRepository.deleteById(id);
    }

    public Author updateAuthor(long id,Author author)
    {
        Optional<Author> existingAuthor=authorRepository.findById(id);
        if(existingAuthor.isPresent())
        {
            Author updated=existingAuthor.get();
            updated.setAuthorName(author.getAuthorName());
            updated.setBio(author.getBio());
            return authorRepository.save(updated);
        }

        return null;
    }

    public Author getAuthorById(long id)
    {
        Optional<Author> existingAuthor=authorRepository.findById(id);
        if(existingAuthor.isPresent())
        {
            Author authorFound=existingAuthor.get();

            return authorFound;
        }
        return null;
    }
}
