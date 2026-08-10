package pk.edu.niit.library_management_system.BorrowRecord.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import pk.edu.niit.library_management_system.BorrowRecord.Entity.BorrowRecord;
import pk.edu.niit.library_management_system.BorrowRecord.Services.BorrowServices;

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
        try{
            List<BorrowRecord> records=borrowServices.getAllBorrowRecords();
            log.info("GET/borrowrecord : {} Borrow Records found",records.size());
            return ResponseEntity.ok(records);
        }
        catch (Exception e)
        {
            log.error("Error getting all borrow records: ",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<BorrowRecord> createBorrowRecord(@RequestBody BorrowRecord borrowRecord)
    {
        try{
            BorrowRecord created=borrowServices.createBorrowRecord(borrowRecord);
            log.info("POST/borrowrecord: Borrow record created with this id: {}",created.getBorrowId());
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        }
        catch (Exception e)
        {
            log.error("Error getting all borrow records");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteBorrowRecord(@PathVariable long id)
    {
        try{
            BorrowRecord existing= borrowServices.getRecordById(id);
            if(existing==null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            borrowServices.deleteBorrowRecord(id);
            log.info("DELETE/borrowrecord/id/{id}: Borrow record deleted for this id : {}",id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (Exception e)
        {
            log.error("Error deleting borrow record: {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateBorrowRecord(@PathVariable long id, @RequestBody BorrowRecord borrowRecord)
    {
        try{
            BorrowRecord updated=borrowServices.updateRecord(id,borrowRecord);
            if(updated==null)
            {
                log.warn("Book not found for this id: {}",id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            log.info("PUT/borrowrecord/id/{}: Borrow record updated for this id: {}",id);
            return ResponseEntity.ok(updated);
        }
        catch (Exception e)
        {
            log.error("Error updating borrow record: {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getBorrowRecordById(@PathVariable long id)
    {
        try{
            BorrowRecord record=borrowServices.getRecordById(id);
            if (record==null)
            {
                log.error("Book not found for this id: {}",id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            log.info("GET/borrowrecord/id/{id}: Book found for this id: {}",id);
            return ResponseEntity.status(HttpStatus.FOUND).body(record);
        }
        catch (Exception e)
        {
            log.error("Error getting book for this id {} :",id,e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("id/{id}/return")
    public ResponseEntity<?> returnBook(@PathVariable long id)
    {
        try{
            borrowServices.returnBook(id);
            log.info("PUT/id/{}/return : Book returned",id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e)
        {
            log.error("Error returning book : ",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
