package pk.edu.niit.library_management_system.BorrowRecord.DTO;

import jakarta.persistence.*;
import lombok.Data;
import pk.edu.niit.library_management_system.Book.Entity.Book;
import pk.edu.niit.library_management_system.BorrowRecord.Util.Statuses;
import pk.edu.niit.library_management_system.Member.Entity.Member;

import java.time.LocalDate;

@Data
public class BorrowRecordResponseDTO {
    private long borrowId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Statuses status;
    private Book book;
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
}
