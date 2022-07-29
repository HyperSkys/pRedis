package dev.hyperskys.predis.redis.exceptions;

/**
 * An exception thrown when PRedis is already running but another instance of PRedis attempted to be started.
 */
public class AlreadyRunningException extends RuntimeException {
    /**
     * Creates a new {@link AlreadyRunningException} with the specified message.
     * @param message The message to be displayed.
     */
    public AlreadyRunningException(String message) {
        super(message);
    }
}
