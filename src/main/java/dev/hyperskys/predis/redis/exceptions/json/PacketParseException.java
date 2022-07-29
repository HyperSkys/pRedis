package dev.hyperskys.predis.redis.exceptions.json;

/**
 * Thrown when an error occurs while parsing a Redis packet.
 */
public class PacketParseException extends RuntimeException {
    /**
     * Creates a new {@link PacketParseException} with the specified message.
     * @param message The message to be displayed.
     */
    public PacketParseException(String message) {
        super(message);
    }
}
