package dev.hyperskys.predis;

import dev.hyperskys.predis.redis.RedisDB;
import dev.hyperskys.predis.redis.events.RedisSubscriber;
import lombok.Getter;

public class PRedis {

    private static @Getter RedisSubscriber redisSubscriber;

    /**
     * This method is used to start the redis subscriber.
     * @param clazz The class that is used in the main method.
     * @param redisDB The RedisDB instance.
     */
    public static void init(Class<?> clazz, RedisDB redisDB) {
        redisSubscriber = new RedisSubscriber(clazz, redisDB);
    }
}