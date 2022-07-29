package dev.hyperskys.predis;

import dev.hyperskys.predis.redis.RedisDB;
import dev.hyperskys.predis.redis.events.RedisSubscriber;
import lombok.Getter;

public class PRedis {

    private static @Getter RedisSubscriber redisSubscriber;

    public static void init(Class<?> clazz, RedisDB redisDB) {
        redisSubscriber = new RedisSubscriber(clazz, redisDB);
    }
}