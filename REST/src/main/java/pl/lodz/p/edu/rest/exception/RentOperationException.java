package pl.lodz.p.edu.rest.exception;

public class RentOperationException extends RuntimeException {
    public RentOperationException(String message) {
        super(message);
    }

    public RentOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
