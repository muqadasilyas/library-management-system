package pk.edu.niit.library_management_system.BorrowRecord.Controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import pk.edu.niit.library_management_system.Book.Services.BookServices;
import pk.edu.niit.library_management_system.BorrowRecord.DTO.BorrowRecordRequestDTO;
import pk.edu.niit.library_management_system.BorrowRecord.DTO.BorrowRecordResponseDTO;
import pk.edu.niit.library_management_system.BorrowRecord.Entity.BorrowRecord;
import pk.edu.niit.library_management_system.BorrowRecord.Services.BorrowServices;
import pk.edu.niit.library_management_system.ExceptionHandler.BorrowRecordNotFoundException;
import pk.edu.niit.library_management_system.Member.Entity.Member;
import pk.edu.niit.library_management_system.Member.Services.MemberServices;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/borrowrecord")
public class BorrowController {
    @Autowired
    private BorrowServices borrowServices;
    @Autowired
    private MemberServices memberServices;
    @Autowired
    private BookServices bookServices;

    @GetMapping
    public ResponseEntity<List<BorrowRecordResponseDTO>> getAll()
    {
            List<BorrowRecord> records=borrowServices.getAllBorrowRecords();
            if(records==null)
            {
                throw new BorrowRecordNotFoundException(
                        "GET/borrowrecord: Borrow records not found"
                );
            }
            List<BorrowRecordResponseDTO> responseDTOS=records.stream().map(borrowRecord -> {
                BorrowRecordResponseDTO responseDTO=new BorrowRecordResponseDTO();
                responseDTO.setBorrowId(borrowRecord.getBorrowId());
                responseDTO.setBorrowDate(borrowRecord.getBorrowDate());
                responseDTO.setBookName(borrowRecord.getBook().getTitle());
                responseDTO.setStatus(borrowRecord.getStatus());
                responseDTO.setDueDate(borrowRecord.getDueDate());
                responseDTO.setMemberName(borrowRecord.getMember().getMemberName());
                responseDTO.setReturnDate(borrowRecord.getReturnDate());
                return responseDTO;
            }).toList();

            log.info("GET/borrowrecord : {} Borrow Records found",responseDTOS.size());
            return ResponseEntity.ok(responseDTOS);

    }

    @PostMapping
    public ResponseEntity<BorrowRecordResponseDTO> createBorrowRecord(@Valid @RequestBody BorrowRecordRequestDTO recordRequestDTO)
    {
            BorrowRecord record=new BorrowRecord();

            record.setMember(memberServices.getMemberById(recordRequestDTO.getMemberId()));
            record.setBook(bookServices.getBookByID(recordRequestDTO.getBookId()));
            record.setReturnDate(recordRequestDTO.getReturnDate());
            record.setBorrowDate(recordRequestDTO.getBorrowDate());
            record.setStatus(recordRequestDTO.getStatus());
            record.setDueDate(recordRequestDTO.getDueDate());
            BorrowRecord created=borrowServices.createBorrowRecord(record);
            BorrowRecordResponseDTO responseDTO=new BorrowRecordResponseDTO();
            responseDTO.setBorrowId(created.getBorrowId());
            responseDTO.setBorrowDate(created.getBorrowDate());
            responseDTO.setBookName(created.getBook().getTitle());
            responseDTO.setStatus(created.getStatus());
            responseDTO.setDueDate(created.getDueDate());
            responseDTO.setMemberName(created.getMember().getMemberName());
            responseDTO.setReturnDate(created.getReturnDate());
            log.info("POST/borrowrecord: Borrow record created with this id: {}",responseDTO.getBorrowId());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteBorrowRecord(@PathVariable long id)
    {

            borrowServices.deleteBorrowRecord(id);
            log.info("DELETE/borrowrecord/id/{id}: Borrow record deleted for this id : {}",id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateBorrowRecord(@PathVariable long id,@Valid @RequestBody BorrowRecordRequestDTO recordRequestDTO)
    {   BorrowRecord borrowRecord=new BorrowRecord();

        borrowRecord.setBorrowDate(recordRequestDTO.getBorrowDate());
        borrowRecord.setMember(memberServices.getMemberById(recordRequestDTO.getMemberId()));
        borrowRecord.setBook(bookServices.getBookByID(recordRequestDTO.getBookId()));
        borrowRecord.setStatus(recordRequestDTO.getStatus());
        borrowRecord.setReturnDate(recordRequestDTO.getReturnDate());
        borrowRecord.setDueDate(recordRequestDTO.getDueDate());
        BorrowRecord updated=borrowServices.updateRecord(id,borrowRecord);

        BorrowRecordResponseDTO responseDTO=new BorrowRecordResponseDTO();
        responseDTO.setBorrowId(borrowRecord.getBorrowId());
        responseDTO.setBorrowDate(borrowRecord.getBorrowDate());
        responseDTO.setStatus(borrowRecord.getStatus());
        responseDTO.setBookName(borrowRecord.getBook().getTitle());
        responseDTO.setMemberName(borrowRecord.getMember().getMemberName());
        responseDTO.setDueDate(borrowRecord.getDueDate());
        responseDTO.setReturnDate(borrowRecord.getReturnDate());

            log.info("PUT/borrowrecord/id/{}: Borrow record updated for this id: {}",id);
            return ResponseEntity.ok(responseDTO);

    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getBorrowRecordById(@PathVariable long id)
    {
            BorrowRecord record=borrowServices.getRecordById(id);
            BorrowRecordResponseDTO responseDTO=new BorrowRecordResponseDTO();
            responseDTO.setBorrowId(record.getBorrowId());
            responseDTO.setBorrowDate(record.getBorrowDate());
            responseDTO.setStatus(record.getStatus());
            responseDTO.setBookName(record.getBook().getTitle());
            responseDTO.setMemberName(record.getMember().getMemberName());
            responseDTO.setDueDate(record.getDueDate());
            responseDTO.setReturnDate(record.getReturnDate());
            log.info("GET/borrowrecord/id/{id}: Book found for this id: {}",id);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

    }

    @PutMapping("id/{id}/return")
    public ResponseEntity<?> returnBook(@PathVariable long id)
    {
            BorrowRecord record=borrowServices.returnBook(id);
            BorrowRecordResponseDTO responseDTO=new BorrowRecordResponseDTO();
            responseDTO.setBorrowId(record.getBorrowId());
            responseDTO.setBookName(record.getBook().getTitle());
            responseDTO.setBorrowDate(record.getBorrowDate());
            responseDTO.setStatus(record.getStatus());
            responseDTO.setMemberName(record.getMember().getMemberName());
            responseDTO.setReturnDate(record.getReturnDate());
            responseDTO.setDueDate(record.getDueDate());
            log.info("PUT/id/{}/return : Book returned",id);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

    }
}
