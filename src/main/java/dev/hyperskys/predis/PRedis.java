package dev.hyperskys.predis;

import dev.hyperskys.predis.redis.events.RedisSubscriber;
import redis.clients.jedis.Jedis;

public class PRedis {
    public static void init(Class<?> clazz, Jedis jedis) {
        new RedisSubscriber(clazz, jedis);
    }
}