package exceptions.train_component_exceptions.passengerCarriage;

public class NotEnoughPlacesException extends RuntimeException {

    private final static String message = "Maximum number of available places: ";

    public NotEnoughPlacesException(int capacity) {
        super(message + capacity);
    }
}
