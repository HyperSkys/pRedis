package dev.hyperskys.predis;

import dev.hyperskys.predis.config.ConfigurationBuilder;
import dev.hyperskys.predis.helper.PRedisHelper;
import dev.hyperskys.predis.redis.exceptions.AlreadyRunningException;

public class PRedis {

    /**
     * Prevent multiple instances of PRedis from being created.
     */
    public static boolean isRunning = false;
    public static Class<?> mainClazz = null;

    /**
     * This method is used to start the redis subscriber.
     * @param configurationBuilder The configuration builder to use.
     */
    public static void init(ConfigurationBuilder configurationBuilder) {
        if (!isRunning || !configurationBuilder.isPreventDuplicateInstances()) {
            new PRedisHelper(configurationBuilder.getClazz(), configurationBuilder.getRedisDB()).init();
            return;
        }

        throw new AlreadyRunningException("PRedis is already running, the method was called more than once.");
    }
}