package by.epamlab.validation.user;

public class EmailExistsException extends  Throwable {
    public EmailExistsException(final String message) {
        super(message);
    }
}
