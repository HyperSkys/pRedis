package dev.hyperskys.predis.config;

import dev.hyperskys.predis.redis.RedisDB;

public class ConfigurationBuilder {

    public RedisDB redisDB;
    public Class<?> clazz;
    public boolean preventDuplicateInstances;

    public ConfigurationBuilder(RedisDB redisDB, Class<?> clazz, boolean preventDuplicateInstances) {
        this.redisDB = redisDB;
        this.clazz = clazz;
        this.preventDuplicateInstances = preventDuplicateInstances;
    }
}
