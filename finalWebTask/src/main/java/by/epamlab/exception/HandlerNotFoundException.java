package by.epamlab.exception;

public class HandlerNotFoundException extends Exception {
    private static final String DEFAULT_MESSAGE = "Exception handler not found.";

    public HandlerNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public HandlerNotFoundException(String message) {
        super(message);
    }
}
