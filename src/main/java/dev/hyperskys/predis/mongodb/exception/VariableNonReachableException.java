package dev.hyperskys.predis.mongodb.exception;

/**
 * An exception thrown when a value is not reachable.
 */
public class VariableNonReachableException extends RuntimeException {
    /**
     * Creates a new {@link VariableNonReachableException} with the specified message.
     * @param message The message to be displayed.
     */
    public VariableNonReachableException(String message) {
        super(message);
    }
}
