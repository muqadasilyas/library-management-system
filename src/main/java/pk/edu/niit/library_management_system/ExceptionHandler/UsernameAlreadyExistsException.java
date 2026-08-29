package pk.edu.niit.library_management_system.ExceptionHandler;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String message)
    {
        super(message);
    }
}
