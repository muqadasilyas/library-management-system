package pk.edu.niit.library_management_system.BorrowRecord.Controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import pk.edu.niit.library_management_system.BorrowRecord.Entity.BorrowRecord;
import pk.edu.niit.library_management_system.BorrowRecord.Services.BorrowServices;
import pk.edu.niit.library_management_system.ExceptionHandler.BorrowRecordNotFoundException;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/borrowrecord")
public class BorrowController {
    @Autowired
    private BorrowServices borrowServices;

    @GetMapping
    public ResponseEntity<List<BorrowRecord>> getAll()
    {
            List<BorrowRecord> records=borrowServices.getAllBorrowRecords();
            if(records==null)
            {
                throw new BorrowRecordNotFoundException(
                        "GET/borrowrecord: Borrow records not found"
                );
            }
            log.info("GET/borrowrecord : {} Borrow Records found",records.size());
            return ResponseEntity.ok(records);

    }

    @PostMapping
    public ResponseEntity<BorrowRecord> createBorrowRecord(@Valid @RequestBody BorrowRecord borrowRecord)
    {
            BorrowRecord created=borrowServices.createBorrowRecord(borrowRecord);

            log.info("POST/borrowrecord: Borrow record created with this id: {}",created.getBorrowId());
            return ResponseEntity.status(HttpStatus.CREATED).body(created);

    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteBorrowRecord(@PathVariable long id)
    {

            borrowServices.deleteBorrowRecord(id);
            log.info("DELETE/borrowrecord/id/{id}: Borrow record deleted for this id : {}",id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateBorrowRecord(@PathVariable long id,@Valid @RequestBody BorrowRecord borrowRecord)
    {
            BorrowRecord updated=borrowServices.updateRecord(id,borrowRecord);
            log.info("PUT/borrowrecord/id/{}: Borrow record updated for this id: {}",id);
            return ResponseEntity.ok(updated);

    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getBorrowRecordById(@PathVariable long id)
    {
            BorrowRecord record=borrowServices.getRecordById(id);
            log.info("GET/borrowrecord/id/{id}: Book found for this id: {}",id);
            return ResponseEntity.status(HttpStatus.OK).body(record);

    }

    @PutMapping("id/{id}/return")
    public ResponseEntity<?> returnBook(@PathVariable long id)
    {
            BorrowRecord record=borrowServices.returnBook(id);
            log.info("PUT/id/{}/return : Book returned",id);
            return ResponseEntity.status(HttpStatus.OK).body(record);

    }
}
