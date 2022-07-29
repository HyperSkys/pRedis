package dev.hyperskys.predis;

import dev.hyperskys.predis.redis.RedisDB;
import dev.hyperskys.predis.redis.events.RedisSubscriber;

public class PRedis {
    public static void init(Class<?> clazz, RedisDB redisDB) {
        new RedisSubscriber(clazz, redisDB);
    }
}