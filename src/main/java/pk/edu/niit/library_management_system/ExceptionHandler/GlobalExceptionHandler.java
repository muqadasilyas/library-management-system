package pk.edu.niit.library_management_system.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<String> handleAuthorNotFound(AuthorNotFoundException e)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFound(BookNotFoundException e)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(BorrowRecordNotFoundException.class)
    public ResponseEntity<String> handleBorrowRecordNotFound(BorrowRecordNotFoundException e)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<String> handleMemberNotFound(MemberNotFoundException e)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(BookNotAvailableException.class)
    public ResponseEntity<String> handleBookNotAvailable(BookNotAvailableException e)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidation(MethodArgumentNotValidException e)
    {
        Map<String,String> errors=new HashMap<>();
                e.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(),error.getDefaultMessage()));

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception e)
    {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<?> handleUserExistError(UsernameAlreadyExistsException e)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

}
