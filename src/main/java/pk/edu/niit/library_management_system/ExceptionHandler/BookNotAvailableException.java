package pk.edu.niit.library_management_system.ExceptionHandler;

public class BookNotAvailableException extends RuntimeException{
    public BookNotAvailableException(String message)
    {
        super(message);
    }
}
