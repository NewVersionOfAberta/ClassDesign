package exceptions.station_exception;

public class CannotManipulateNullCarriageException extends RuntimeException {
    private final static String message = "Value of carriage must not be null";

    public CannotManipulateNullCarriageException() {
        super(message);
    }

}
