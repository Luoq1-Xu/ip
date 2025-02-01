package nyx.exceptions;

/**
 * Represents an exception that is thrown when a command is used incorrectly.
 */
public class InvalidUsageException extends NyxException {

    /**
     * Constructs a new InvalidUsageException with the specified detail message.
     *
     * @param message The detail message.
     */
    public InvalidUsageException(String message) {
        super(message);
    }
}
