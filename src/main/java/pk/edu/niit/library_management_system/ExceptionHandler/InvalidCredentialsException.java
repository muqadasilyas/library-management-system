package pk.edu.niit.library_management_system.ExceptionHandler;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(String message )
    {

        super(message);
    }
}
