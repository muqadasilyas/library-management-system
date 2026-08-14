package pk.edu.niit.library_management_system.BorrowRecord.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pk.edu.niit.library_management_system.Book.Entity.Book;
import pk.edu.niit.library_management_system.BorrowRecord.Util.Statuses;
import pk.edu.niit.library_management_system.Member.Entity.Member;

import java.time.LocalDate;

@Data
public class BorrowRecordRequestDTO {
    @NotNull(message = "Borrow date should not be empty")
    private LocalDate borrowDate;
    @NotNull(message = "Due date should not be empty")
    private LocalDate dueDate;
    private LocalDate returnDate;
    @NotBlank(message = "Status must not be blank")
    private Statuses status;
    @NotNull
    private Book book;
    @NotNull
    private Member member;
}
