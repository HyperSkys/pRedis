package dev.hyperskys.predis.helper;

import dev.hyperskys.predis.PRedis;
import dev.hyperskys.predis.redis.RedisDB;
import dev.hyperskys.predis.redis.events.RedisSubscriber;
import lombok.SneakyThrows;

public class PRedisHelper {

    private final RedisDB redis;
    private final Class<?> mainClazz;

    /**
     * Creates an instance of PRedisHelper.
     * @param main Clazz that is used in the main method.
     * @param redisDB The RedisDB instance.
     */
    public PRedisHelper(Class<?> main, RedisDB redisDB) {
        redis = redisDB;
        mainClazz = main;
    }

    /**
     * This method is used to start the PRedis helper class.
     */
    @SneakyThrows
    public void init() {
        PRedis.class.getDeclaredField("isRunning").set(null, true);
        PRedis.class.getDeclaredField("mainClazz").set(null, mainClazz);
        new RedisSubscriber(redis).init();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                PRedis.class.getDeclaredField("isRunning").set(null, false);
                redis.close();
            } catch (IllegalAccessException | NoSuchFieldException ignored) {}
        }));
    }
}
