package dev.hyperskys.predis.redis.exceptions.json;

/**
 * An exception thrown when a value that is gathered via the data object but returns null.
 */
public class UsedValueReturnNull extends NullPointerException {
    /**
     * Creates a new {@link UsedValueReturnNull} with the specified message.
     * @param message The message to be displayed.
     */
    public UsedValueReturnNull(String message) {
        super(message);
    }
}
