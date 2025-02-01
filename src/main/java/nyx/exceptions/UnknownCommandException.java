package nyx.exceptions;

/**
 * Represents an exception that is thrown when an unknown command is encountered.
 */
public class UnknownCommandException extends NyxException {

    /**
     * Constructs a new UnknownCommandException with the specified detail message.
     *
     * @param message The detail message.
     */
    public UnknownCommandException(String message) {
        super(message);
    }
}
