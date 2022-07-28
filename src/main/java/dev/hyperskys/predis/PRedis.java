package dev.hyperskys.predis;

import lombok.Getter;
import lombok.Setter;

public class PRedis {

    private static @Getter @Setter Class<?> mainClazz;

    public static void init(Class<?> clazz) {
        setMainClazz(clazz);
    }
}