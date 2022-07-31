package dev.hyperskys.predis.test;

import dev.hyperskys.predis.mongodb.mongo.annotations.GetDocument;
import org.bson.Document;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;

import static org.reflections.scanners.Scanners.FieldsAnnotated;

public class ExtendTest {

    private static @GetDocument(database = "e", collection = "b", key = "c") String key;

    public static void main(String[] args) throws IllegalAccessException {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackage("dev.hyperskys.predis")
                .setScanners(FieldsAnnotated)
        );

        for (Field field : reflections.getFieldsAnnotatedWith(GetDocument.class)) {
            field.setAccessible(true);
            field.set(null, new Document("test", "example"));
        }
    }
}
