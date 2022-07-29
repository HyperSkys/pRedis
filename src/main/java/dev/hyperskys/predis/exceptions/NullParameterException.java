package dev.hyperskys.predis.exceptions;

public class NullParameterException extends NullPointerException {
    public NullParameterException(String message) {
        super(message);
    }
}
