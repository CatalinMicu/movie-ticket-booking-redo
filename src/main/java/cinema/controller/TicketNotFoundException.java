package cinema.controller;

public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException(String message) {
        super(message);
    }

    public TicketNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TicketNotFoundException(Throwable cause) {
        super(cause);
    }
}
