package pk.edu.niit.library_management_system.ExceptionHandler;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(String message)
    {
        super(message);
    }
}
