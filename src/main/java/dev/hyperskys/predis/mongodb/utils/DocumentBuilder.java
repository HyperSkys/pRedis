package dev.hyperskys.predis.mongodb.utils;

import org.bson.Document;

/**
 * A utility class for building BSON documents.
 */
public class DocumentBuilder {

    /**
     * The document that is being built.
     */
    private final Document document;

    /**
     * Creates a new {@link DocumentBuilder} with the specified document.
     * @param key The key to be put in the document.
     * @param value The value to be put in the key of the document.
     */
    public DocumentBuilder(String key, Object value) {
        document = new org.bson.Document(key, value);
    }

    /**
     * Adds a new key-value pair to the document.
     * @param key The key to be put in the document.
     * @param value The value to be put in the key of the document.
     * @return The document builder.
     */
    public DocumentBuilder add(String key, Object value) {
        document.put(key, value);
        return this;
    }

    public Document build() {
        return document;
    }
}
