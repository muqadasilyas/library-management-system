package pk.edu.niit.library_management_system.BorrowRecord.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pk.edu.niit.library_management_system.Book.Services.BookServices;
import pk.edu.niit.library_management_system.BorrowRecord.DTO.BorrowRecordRequestDTO;
import pk.edu.niit.library_management_system.BorrowRecord.DTO.BorrowRecordResponseDTO;
import pk.edu.niit.library_management_system.BorrowRecord.Entity.BorrowRecord;
import pk.edu.niit.library_management_system.Member.Services.MemberServices;

@Component
public class BorrowMapper {
    @Autowired
    MemberServices memberServices;
    @Autowired
    BookServices bookServices;
    public BorrowRecord toEntity(BorrowRecordRequestDTO recordRequestDTO)
    {
        BorrowRecord record=new BorrowRecord();
        record.setMember(memberServices.getMemberById(recordRequestDTO.getMemberId()));
        record.setBook(bookServices.getBookByID(recordRequestDTO.getBookId()));
        record.setReturnDate(recordRequestDTO.getReturnDate());
        record.setBorrowDate(recordRequestDTO.getBorrowDate());
        record.setStatus(recordRequestDTO.getStatus());
        record.setDueDate(recordRequestDTO.getDueDate());
        return record;
    }
    public BorrowRecordResponseDTO toResponseDTO(BorrowRecord borrowRecord)
    {
        BorrowRecordResponseDTO responseDTO=new BorrowRecordResponseDTO();
        responseDTO.setBorrowId(borrowRecord.getBorrowId());
        responseDTO.setBorrowDate(borrowRecord.getBorrowDate());
        responseDTO.setBookName(borrowRecord.getBook().getTitle());
        responseDTO.setStatus(borrowRecord.getStatus());
        responseDTO.setDueDate(borrowRecord.getDueDate());
        responseDTO.setMemberName(borrowRecord.getMember().getMemberName());
        responseDTO.setReturnDate(borrowRecord.getReturnDate());
        return responseDTO;
    }
}
