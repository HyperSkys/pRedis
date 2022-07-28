package dev.hyperskys.predis.example;

import dev.hyperskys.predis.PRedis;
import dev.hyperskys.predis.redis.RedisDB;
import dev.hyperskys.predis.utils.PacketBuilder;

public class PacketSender {

    private static final RedisDB redisDB = new RedisDB(
            "localhost",
            6379,
            false,
            null,
            5000
    );

    public static void main(String[] args) {
        PRedis.setMainClazz(PacketSender.class);
        redisDB.write(new PacketBuilder("broadcast").addData("message", "Hello, World!").build());
    }
}
