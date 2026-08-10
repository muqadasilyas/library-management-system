package pk.edu.niit.library_management_system.BorrowRecord.Services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pk.edu.niit.library_management_system.BorrowRecord.Entity.BorrowRecord;
import pk.edu.niit.library_management_system.BorrowRecord.Repository.BorrowRepository;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class BorrowServices {
    @Autowired
    private BorrowRepository borrowRepository;

    public List<BorrowRecord> getAllBorrowRecords()
    {
        return borrowRepository.findAll();
    }

    public BorrowRecord createBorrowRecord(BorrowRecord borrowRecord)
    {
        return borrowRepository.save(borrowRecord);
    }

    public void deleteBorrowRecord(long id)
    {
        borrowRepository.deleteById(id);
    }

    public BorrowRecord updateRecord(long id, BorrowRecord borrowRecord)
    {
        Optional<BorrowRecord> existingRecord=borrowRepository.findById(id);
        if(existingRecord.isPresent())
        {
            BorrowRecord updated=existingRecord.get();
            updated.setBook(borrowRecord.getBook());
            updated.setBorrowDate(borrowRecord.getBorrowDate());
            updated.setMember(borrowRecord.getMember());
            updated.setStatus(borrowRecord.getStatus());
            updated.setDueDate(borrowRecord.getDueDate());
            updated.setReturnDate(borrowRecord.getReturnDate());
            return borrowRepository.save(updated);
        }
        return null;
    }

    public BorrowRecord getRecordById(long id)
    {

        Optional<BorrowRecord> existingRecord= borrowRepository.findById(id);
        if(existingRecord.isPresent())
        {
            BorrowRecord record=existingRecord.get();
            return record;
        }
        return null;
    }
}
