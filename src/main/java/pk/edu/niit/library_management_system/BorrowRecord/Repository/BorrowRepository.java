package pk.edu.niit.library_management_system.BorrowRecord.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pk.edu.niit.library_management_system.BorrowRecord.Entity.BorrowRecord;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowRecord,Long> {


}
