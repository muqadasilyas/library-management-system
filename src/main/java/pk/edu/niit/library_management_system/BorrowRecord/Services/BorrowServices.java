package pk.edu.niit.library_management_system.BorrowRecord.Services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pk.edu.niit.library_management_system.Book.Entity.Book;
import pk.edu.niit.library_management_system.Book.Repository.BookRepository;
import pk.edu.niit.library_management_system.BorrowRecord.Entity.BorrowRecord;
import pk.edu.niit.library_management_system.BorrowRecord.Repository.BorrowRepository;
import pk.edu.niit.library_management_system.BorrowRecord.Util.Statuses;
import pk.edu.niit.library_management_system.ExceptionHandler.BookNotFoundException;
import pk.edu.niit.library_management_system.ExceptionHandler.BorrowRecordNotFoundException;
import pk.edu.niit.library_management_system.Member.Entity.Member;
import pk.edu.niit.library_management_system.Member.Repository.MemberRepository;

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
    @Autowired
    private MemberRepository memberRepository;

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
            throw new BookNotFoundException("Book not found for this borrow record: "+ borrowRecord);
        }
        Long memberID=borrowRecord.getMember().getMemberId();
        Optional<Member> existingMember=memberRepository.findById(memberID);
        if(existingMember.isEmpty())
        {
            return null;
        }
        Book book=existing.get();
        if(book.getAvailableCopies()<=0)
        {
            throw new BookNotFoundException("Book not available to be borrowed for id: "+ bookId);
        }
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
        borrowRecord.setBook(book);
        borrowRecord.setMember(existingMember.get());
        borrowRecord.setStatus(Statuses.BORROWED);
        return borrowRepository.save(borrowRecord);
    }

    public void deleteBorrowRecord(long id)
    {
        if(!borrowRepository.existsById(id))
        {
            throw new BorrowRecordNotFoundException("Record not found for id: "+id);
        }
        borrowRepository.deleteById(id);
    }

    public BorrowRecord updateRecord(long id, BorrowRecord borrowRecord)
    {
       BorrowRecord existingRecord=borrowRepository.findById(id).orElseThrow(()->
               new BorrowRecordNotFoundException("Record not found for id: "+id));
            existingRecord.setBook(borrowRecord.getBook());
            existingRecord.setBorrowDate(borrowRecord.getBorrowDate());
            existingRecord.setMember(borrowRecord.getMember());
            existingRecord.setStatus(borrowRecord.getStatus());
            existingRecord.setDueDate(borrowRecord.getDueDate());
            existingRecord.setReturnDate(borrowRecord.getReturnDate());
            return borrowRepository.save(existingRecord);

    }

    public BorrowRecord getRecordById(long id)
    {

        BorrowRecord existingRecord= borrowRepository.findById(id).orElseThrow(()->
                new BorrowRecordNotFoundException("Record not found for id : "+id));


        return existingRecord;

    }

    public BorrowRecord returnBook(long id)
    {
        BorrowRecord existing=borrowRepository.findById(id).orElseThrow(()->
                new BorrowRecordNotFoundException("Record not found for id: "+id));

        if(existing.getStatus()==Statuses.RETURNED)
        {
            return existing;
        }
        Book book=existing.getBook();
        book.setAvailableCopies(book.getAvailableCopies()+1);
        bookRepository.save(book);

        existing.setStatus(Statuses.RETURNED);
        existing.setReturnDate(LocalDate.now());
        return borrowRepository.save(existing);
    }
}
