package cinema.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(MovieNotFoundException e) {
        return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(TicketNotFoundException e) {
        return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(UserNotFoundException e) {
        return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllOtherExceptions(Exception e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return buildErrorResponse(e.getMessage(), status);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(String message, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),
                message,
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, status);
    }
}