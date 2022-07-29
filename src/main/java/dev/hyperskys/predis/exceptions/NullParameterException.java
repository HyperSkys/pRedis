package dev.hyperskys.predis.exceptions;

/**
 * An exception thrown when a parameter that cannot null returns null.
 */
public class NullParameterException extends NullPointerException {
    /**
     * Creates a new {@link NullParameterException} with the specified message.
     * @param message The message to be displayed.
     */
    public NullParameterException(String message) {
        super(message);
    }
}
