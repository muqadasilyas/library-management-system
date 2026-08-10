package pk.edu.niit.library_management_system.Member.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pk.edu.niit.library_management_system.Member.Entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
}
