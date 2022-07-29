package dev.hyperskys.predis.exceptions;

public class PacketParseException extends RuntimeException {
    public PacketParseException(String message) {
        super(message);
    }
}
