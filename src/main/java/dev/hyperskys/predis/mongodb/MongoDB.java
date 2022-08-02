package dev.hyperskys.predis.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import dev.hyperskys.predis.mongodb.exception.MongoLoginException;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

/**
 * MongoDB is a wrapper client for Mongo Java Driver.
 */
public class MongoDB {

    /**
     * The MongoDB client.
     */
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
     * @param databaseName The name of the database, will create one if it doesn't exist.
     * @param collectionName The name of the collection.
     * @return The collection.
     */
    public MongoCollection<Document> getCollection(String databaseName, String collectionName) {
       return getDatabase(databaseName).getCollection(collectionName);
    }

    /**
     * Inserts a new {@link Document} into the specified collection in the database.
     * @param databaseName The name of the database, will create one if it doesn't exist.
     * @param collectionName The name of the collection, will create one if it doesn't exist.
     * @param document The document to be inserted.
     */
    public void insertDocument(String databaseName, String collectionName, Document document) {
        getCollection(databaseName, collectionName).insertOne(document);
    }

    /**
     * Finds a document in the specified collection in the database.
     * @param databaseName The name of the database, will create one if it doesn't exist.
     * @param collectionName The name of the collection, will create one if it doesn't exist.
     * @param key The key of the document.
     * @return The document with the specified key.
     */
    public Document findDocument(String databaseName, String collectionName, String key) {
        return getCollection(databaseName, collectionName).find(Filters.eq(key)).first();
    }

    /**
     * Checks if the specified collection exists in the database.
     * @param databaseName The name of the database, will create one if it doesn't exist.
     * @param collectionName The name of the collection, will create one if it doesn't exist.
     * @param document The document to check if it exists.
     * @return True if the collection exists, false otherwise.
     */
    public boolean containsDocument(String databaseName, String collectionName, Document document) {
        return getCollection(databaseName, collectionName).find(document).first() != null;
    }

    /**
     * Updates a {@link Document} in the specified collection in the database.
     * @param databaseName The name of the database, will create one if it doesn't exist.
     * @param collectionName The name of the collection, will create one if it doesn't exist.
     * @param filter The old document that will be replaced.
     * @param document The new document that will replace the old one.
     */
    public void replaceDocument(String databaseName, String collectionName, Document filter, Document document) {
        getCollection(databaseName, collectionName).replaceOne(filter, document);
    }

    /**
     * Deletes a {@link Document} in the specified collection in the database.
     * @param databaseName The name of the database, will create one if it doesn't exist.
     * @param collectionName The name of the collection, will create one if it doesn't exist.
     * @param filter The document that will be deleted.
     */
    public void removeDocument(String databaseName, String collectionName, Document filter) {
        getCollection(databaseName, collectionName).findOneAndDelete(filter);
    }

    /**
     * Closes the connection to the database.
     * @return If the connection was closed successfully.
     * @apiNote If you do not close the client it may cause memory leaks.
     */
    public boolean close() {
        try {
            getMongoClient().close();
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
}
