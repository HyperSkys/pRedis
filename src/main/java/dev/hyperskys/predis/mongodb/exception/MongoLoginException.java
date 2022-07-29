package dev.hyperskys.predis.mongodb.exception;

/**
 * An exception thrown when the mongodb client failed to connect.
 */
public class MongoLoginException extends RuntimeException {
    /**
     * Creates a new {@link MongoLoginException} with the specified message.
     * @param message The message to be displayed.
     */
    public MongoLoginException(String message) {
        super(message);
    }
}
