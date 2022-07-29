package dev.hyperskys.predis.exceptions;

/**
 * Thrown when a message cannot be published to a channel.
 */
public class PublishException extends RuntimeException {
    /**
     * Creates a new {@link PublishException} with the specified message.
     * @param message The message to be displayed.
     */
    public PublishException(String message) {
        super(message);
    }
}
