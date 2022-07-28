package dev.hyperskys.predis.exceptions;

public class AnnotatedClassException extends RuntimeException {
    public AnnotatedClassException(String reason) {
        super(reason);
    }
}
