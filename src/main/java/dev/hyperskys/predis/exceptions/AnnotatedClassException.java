package dev.hyperskys.predis.exceptions;

/**
 * An exception thrown when an annotated class does not extend {@link dev.hyperskys.predis.redis.packets.RedisPacket}.
 */
public class AnnotatedClassException extends RuntimeException {
    /**
     * Creates a new {@link AnnotatedClassException} with the specified message.
     * @param message The message to be displayed.
     */
    public AnnotatedClassException(String message) {
        super(message);
    }
}
