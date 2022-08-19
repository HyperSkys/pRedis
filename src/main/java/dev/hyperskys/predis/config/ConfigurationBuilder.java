package dev.hyperskys.predis.config;

import dev.hyperskys.predis.redis.RedisDB;
import lombok.Getter;

public class ConfigurationBuilder {

    private final @Getter RedisDB redisDB;
    private final @Getter Class<?> clazz;
    private final @Getter boolean preventDuplicateInstances;

    public ConfigurationBuilder(RedisDB redisDB, Class<?> clazz, boolean preventDuplicateInstances) {
        this.redisDB = redisDB;
        this.clazz = clazz;
        this.preventDuplicateInstances = preventDuplicateInstances;
    }
}
