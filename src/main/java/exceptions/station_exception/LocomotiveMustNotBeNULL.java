package exceptions.station_exception;

public class LocomotiveMustNotBeNULL extends RuntimeException {
    private final static String message = "Value of locomotive must not be null";

    public LocomotiveMustNotBeNULL() {
        super(message);
    }
}
