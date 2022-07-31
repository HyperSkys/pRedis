package dev.hyperskys.predis.mongodb.exception;

/**
 * An exception thrown when a value could not be found in the mongodb document.
 */
public class ValueNotFoundException extends RuntimeException {
    /**
     * Creates a new {@link ValueNotFoundException} with the specified message.
     * @param message The message to be displayed.
     */
    public ValueNotFoundException(String message) {
        super(message);
    }
}
