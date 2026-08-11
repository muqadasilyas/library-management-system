package pk.edu.niit.library_management_system.ExceptionHandler;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String message)
    {
        super(message);
    }
}
