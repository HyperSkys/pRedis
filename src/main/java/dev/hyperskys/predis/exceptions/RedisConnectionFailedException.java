package dev.hyperskys.predis.exceptions;

/**
 * An exception thrown when the redis client failed to connect.
 */
public class RedisConnectionFailedException extends RuntimeException {
    /**
     * Creates a new {@link RedisConnectionFailedException} with the specified message.
     * @param message The message to be displayed.
     */
    public RedisConnectionFailedException(String message) {
        super(message);
    }
}
