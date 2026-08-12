package pk.edu.niit.library_management_system.ExceptionHandler;

public class BorrowRecordNotFoundException extends RuntimeException{
    public BorrowRecordNotFoundException(String msg)
    {
        super(msg);
    }
}
