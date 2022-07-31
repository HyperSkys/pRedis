package dev.hyperskys.predis.mongodb.mongo;

import com.mongodb.client.model.Filters;
import dev.hyperskys.predis.PRedis;
import dev.hyperskys.predis.mongodb.MongoDB;
import dev.hyperskys.predis.mongodb.exception.ValueNotFoundException;
import dev.hyperskys.predis.mongodb.exception.VariableNonDocumentException;
import dev.hyperskys.predis.mongodb.exception.VariableNonReachableException;
import dev.hyperskys.predis.mongodb.mongo.annotations.GetDocument;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;

import static org.reflections.scanners.Scanners.FieldsAnnotated;

/**
 * The annotation handler for the {@link GetDocument} annotation.
 */
public class AnnotationHandler {

    /**
     * The MongoDB instance.
     */
    private static MongoDB mongoDB;

    /**
     * Initializes the annotation handler.
     * @param mongoDB The MongoDB instance.
     */
    public AnnotationHandler(MongoDB mongoDB) {
        AnnotationHandler.mongoDB = mongoDB;
    }

    /**
     * Initialize the annotation handler.
     */
    public void init() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackage(PRedis.mainClazz.getPackage().getName())
                .setScanners(FieldsAnnotated)
        );

        for (Field field : reflections.getFieldsAnnotatedWith(GetDocument.class)) {
            String databaseName = field.getAnnotation(GetDocument.class).database();
            String collectionName = field.getAnnotation(GetDocument.class).collection();
            String key = field.getAnnotation(GetDocument.class).key();
            try {
                field.setAccessible(true);
                if (mongoDB.getCollection(databaseName, collectionName).find(Filters.eq(key, field.get(null))).first() == null)
                    throw new ValueNotFoundException("Value not found in database: " + databaseName + "." + collectionName + "." + key);
                field.set(null, mongoDB.getCollection(databaseName, collectionName).find(Filters.eq(key)).first());
            } catch (IllegalAccessException e) {
                throw new VariableNonReachableException("The variable " + field.getName() + " is not static.");
            } catch (IllegalArgumentException e) {
                throw new VariableNonDocumentException("The variable " + field.getName() + " is not a document.");
            }
        }
    }
}
