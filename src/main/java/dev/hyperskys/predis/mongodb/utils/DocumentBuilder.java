package dev.hyperskys.predis.mongodb.utils;

import org.bson.Document;

public class DocumentBuilder {

    private final Document document;

    public DocumentBuilder(String key, Object value) {
        document = new org.bson.Document(key, value);
    }

    public DocumentBuilder add(String key, Object value) {
        document.put(key, value);
        return this;
    }

    public Document build() {
        return document;
    }
}
