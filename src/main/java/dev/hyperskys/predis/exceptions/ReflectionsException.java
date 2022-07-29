package dev.hyperskys.predis.exceptions;

/**
 * An exception thrown when a problem occurs during reflection.
 */
public class ReflectionsException extends RuntimeException {
    /**
     * Creates a new {@link ReflectionsException} with the specified message.
     * @param message The message to be displayed.
     */
    public ReflectionsException(String message) {
        super(message);
    }
}
