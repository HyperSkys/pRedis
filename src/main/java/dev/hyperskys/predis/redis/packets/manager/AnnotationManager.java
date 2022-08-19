package dev.hyperskys.predis.redis.packets.manager;

import dev.hyperskys.predis.PRedis;
import dev.hyperskys.predis.redis.RedisDB;
import dev.hyperskys.predis.redis.packets.annotations.GetMapping;
import dev.hyperskys.predis.redis.packets.annotations.SetMapping;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;

import java.lang.reflect.Field;

public class AnnotationManager {
    public static void setupAnnotationsManager(RedisDB redisDB) {
        Reflections reflections = new Reflections(PRedis.mainClazz.getPackage().getName(), new FieldAnnotationsScanner());
        for (Field field : reflections.getFieldsAnnotatedWith(GetMapping.class)) {
            try {
                String key = field.getAnnotation(GetMapping.class).key();
                field.setAccessible(true);
                field.set(null, redisDB.get(key));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        for (Field field : reflections.getFieldsAnnotatedWith(SetMapping.class)) {
            try {
                String key = field.getAnnotation(SetMapping.class).key();
                String value = field.getAnnotation(SetMapping.class).value();
                redisDB.set(key, value);
                field.setAccessible(true);
                field.set(null, redisDB.get(key));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
