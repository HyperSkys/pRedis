package dev.hyperskys.predis.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dev.hyperskys.predis.mongodb.exception.MongoLoginException;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

/**
 * MongoDB is a wrapper client for Mongo Java Driver.
 */
public class MongoDB {

    private static @Getter @Setter MongoClient mongoClient;

    /**
     * Creates a new {@link MongoDB} with the specified host and port.
     * @param connectionString The connection string.
     */
    public MongoDB(String connectionString) {
        setMongoClient(MongoClients.create(connectionString));

        try {
            mongoClient.startSession();
        } catch (Exception exception) {
            throw new MongoLoginException("Failed to connect to MongoDB server, try checking your credentials.");
        }
    }

    /**
     * Creates a new {@link MongoDatabase} if it does not exist, otherwise returns the existing one.
     * @param databaseName The name of the database.
     * @return The database.
     */
    public MongoDatabase getDatabase(String databaseName) {
        return getMongoClient().getDatabase(databaseName);
    }

    /**
     * Creates a new {@link MongoCollection} if it does not exist, otherwise returns the existing one.
     * @param databaseName The name of the database.
     * @param collectionName The name of the collection.
     * @return The collection.
     */
    public MongoCollection<Document> getCollection(String databaseName, String collectionName) {
       return getDatabase(databaseName).getCollection(collectionName);
    }

    /**
     * Inserts a new {@link Document} into the specified collection in the database.
     * @param databaseName The name of the database.
     * @param collectionName The name of the collection.
     * @param document The document to be inserted.
     */
    public void insertDocument(String databaseName, String collectionName, Document document) {
        getCollection(databaseName, collectionName).insertOne(document);
    }

    /**
     * Updates a {@link Document} in the specified collection in the database.
     * @param databaseName The name of the database.
     * @param collectionName The name of the collection.
     * @param filter The old document that will be replaced.
     * @param document The new document that will replace the old one.
     */
    public void replaceDocument(String databaseName, String collectionName, Document filter, Document document) {
        getCollection(databaseName, collectionName).replaceOne(filter, document);
    }

    /**
     * Deletes a {@link Document} in the specified collection in the database.
     * @param databaseName The name of the database.
     * @param collectionName The name of the collection.
     * @param filter The document that will be deleted.
     */
    public void removeDocument(String databaseName, String collectionName, Document filter) {
        getCollection(databaseName, collectionName).findOneAndDelete(filter);
    }

    /**
     * Closes the connection to the database.
     */
    public void close() {
        getMongoClient().close();
    }
}
