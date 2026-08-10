package pk.edu.niit.library_management_system.BorrowRecord.Services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pk.edu.niit.library_management_system.Book.Entity.Book;
import pk.edu.niit.library_management_system.Book.Repository.BookRepository;
import pk.edu.niit.library_management_system.BorrowRecord.Entity.BorrowRecord;
import pk.edu.niit.library_management_system.BorrowRecord.Repository.BorrowRepository;
import pk.edu.niit.library_management_system.BorrowRecord.Util.Statuses;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class BorrowServices {
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private BookRepository bookRepository;

    public List<BorrowRecord> getAllBorrowRecords()
    {
        return borrowRepository.findAll();
    }

    public BorrowRecord createBorrowRecord(BorrowRecord borrowRecord)
    {
        Long bookId=borrowRecord.getBook().getBookId();
        Optional<Book> existing= bookRepository.findById(bookId);
        if(existing.isEmpty())
        {
            return null;
        }
        Book book=existing.get();
        if(book.getAvailableCopies()<=0)
        {
            return null;
        }
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
        borrowRecord.setBook(book);
        borrowRecord.setStatus(Statuses.BORROWED);
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

    public BorrowRecord returnBook(long id)
    {
        Optional<BorrowRecord> existing=borrowRepository.findById(id);
        if(existing.isEmpty())
        {
            return null;
        }
        BorrowRecord record=existing.get();
        if(record.getStatus()==Statuses.RETURNED)
        {
            return record;
        }
        Book book=record.getBook();
        book.setAvailableCopies(book.getAvailableCopies()+1);
        bookRepository.save(book);

        record.setStatus(Statuses.RETURNED);
        record.setReturnDate(LocalDate.now());
        return borrowRepository.save(record);
    }
}
