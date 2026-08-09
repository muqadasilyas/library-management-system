package pk.edu.niit.library_management_system.Book.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pk.edu.niit.library_management_system.Book.Entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

}
