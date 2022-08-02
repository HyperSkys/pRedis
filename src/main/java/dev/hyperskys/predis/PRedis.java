package dev.hyperskys.predis;

import dev.hyperskys.predis.redis.RedisDB;
import dev.hyperskys.predis.redis.events.RedisSubscriber;
import dev.hyperskys.predis.redis.exceptions.AlreadyRunningException;
import lombok.NonNull;

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
    public static void init(@NonNull Class<?> clazz, @NonNull RedisDB redisDB) {
        if (!isRunning) {
            new RedisSubscriber(redisDB).init();
            isRunning = true;
            mainClazz = clazz;
            return;
        }

        throw new AlreadyRunningException("PRedis is already running, the method was called more than once.");
    }
}