package pk.edu.niit.library_management_system.BorrowRecord.Entity;

import jakarta.persistence.*;
import lombok.Data;
import pk.edu.niit.library_management_system.Book.Entity.Book;
import pk.edu.niit.library_management_system.BorrowRecord.Util.Statuses;
import pk.edu.niit.library_management_system.Member.Entity.Member;

import java.time.LocalDate;

@Entity
@Table(name="BorrowRecord")
@Data
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long borrowId;
    @Column
    private LocalDate borrowDate;
    @Column
    private LocalDate dueDate;
    @Column(nullable = true)
    private LocalDate returnDate;
    @Enumerated(EnumType.STRING)
    @Column
    private Statuses status;
    @OneToOne
    @JoinColumn(name = "bookId")
    private Book book;
    @OneToOne
    @JoinColumn(name = "memberId")
    private Member member;

}
