package dev.hyperskys.predis.mongodb.exception;

/**
 * An exception thrown when a variable is not a document.
 */
public class VariableNonDocumentException extends RuntimeException {
    /**
     * Creates a new {@link VariableNonDocumentException} with the specified message.
     * @param message The message to be displayed.
     */
    public VariableNonDocumentException(String message) {
        super(message);
    }
}
