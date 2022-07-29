package dev.hyperskys.predis;

import dev.hyperskys.predis.redis.RedisDB;
import dev.hyperskys.predis.redis.events.RedisSubscriber;
import dev.hyperskys.predis.redis.exceptions.AlreadyRunningException;

public class PRedis {

    /**
     * Prevent multiple instances of PRedis from being created.
     */
    private static boolean isRunning = false;

    /**
     * This method is used to start the redis subscriber.
     * @param clazz The class that is used in the main method.
     * @param redisDB The RedisDB instance.
     */
    public static void init(Class<?> clazz, RedisDB redisDB) {
        if (!isRunning) {
            new RedisSubscriber(clazz, redisDB).init();
            isRunning = true;
            return;
        }

        throw new AlreadyRunningException("PRedis is already running, the method was called more than once.");
    }
}