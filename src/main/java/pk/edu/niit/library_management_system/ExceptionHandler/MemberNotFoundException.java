package pk.edu.niit.library_management_system.ExceptionHandler;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException(String message)
    {
        super(message);
    }
}
