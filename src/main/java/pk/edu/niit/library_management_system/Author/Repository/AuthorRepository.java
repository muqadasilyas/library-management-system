package pk.edu.niit.library_management_system.Author.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pk.edu.niit.library_management_system.Author.Entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
}
