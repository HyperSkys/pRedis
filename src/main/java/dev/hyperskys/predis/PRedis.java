package dev.hyperskys.predis;

import dev.hyperskys.predis.mongodb.MongoDB;
import dev.hyperskys.predis.mongodb.mongo.AnnotationHandler;
import dev.hyperskys.predis.redis.RedisDB;
import dev.hyperskys.predis.redis.events.RedisSubscriber;
import dev.hyperskys.predis.redis.exceptions.AlreadyRunningException;
import lombok.NonNull;

import javax.annotation.Nullable;

public class PRedis {

    /**
     * Prevent multiple instances of PRedis from being created.
     */
    private static boolean isRunning = false;
    public static Class<?> mainClazz = null;

    /**
     * This method is used to start the redis subscriber.
     * @param clazz The class that is used in the main method.
     * @param redisDB The RedisDB instance.
     */
    public static void init(@NonNull Class<?> clazz, @Nullable RedisDB redisDB, @Nullable MongoDB mongoDB) {
        if (!isRunning) {
            if (redisDB != null) {
                new RedisSubscriber(redisDB).init();
            }

            if (mongoDB != null) {
                new AnnotationHandler(mongoDB).init();
            }

            if (mongoDB == null && redisDB == null) {
                throw new IllegalArgumentException("You must provide at-least a RedisDB or MongoDB instance.");
            }

            isRunning = true;
            mainClazz = clazz;
            return;
        }

        throw new AlreadyRunningException("PRedis is already running, the method was called more than once.");
    }
}