package nyx.exceptions;

/**
 * Represents a generic exception in the Nyx application.
 */
public class NyxException extends Exception {

    /**
     * Constructs a new NyxException with the specified detail message.
     *
     * @param message The detail message.
     */
    public NyxException(String message) {
        super(message);
    }
}
