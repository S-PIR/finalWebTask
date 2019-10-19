package by.epamlab.exception;

public class QuantityOutOfRangeException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Quantity is out of range.";

    public QuantityOutOfRangeException() {
        super(DEFAULT_MESSAGE);
    }

    public QuantityOutOfRangeException(String message) {
        super(message);
    }
}
