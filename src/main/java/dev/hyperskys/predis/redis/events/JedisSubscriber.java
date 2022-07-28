package dev.hyperskys.predis.redis.events;

import dev.hyperskys.predis.PRedis;
import dev.hyperskys.predis.exceptions.AnnotatedClassException;
import dev.hyperskys.predis.redis.packets.RedisPacket;
import dev.hyperskys.predis.redis.packets.annotations.Packet;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.reflections.Reflections;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.ArrayList;
import java.util.Objects;

public class JedisSubscriber {
    private static final ArrayList<RedisPacket> redisPackets = new ArrayList<>();
    private static final Reflections reflections = new Reflections(PRedis.getMainClazz());

    public static void init(Jedis jedis) {
        for (Class<?> clazz : reflections.getTypesAnnotatedWith(Packet.class)) {
            try {
                redisPackets.add((RedisPacket) clazz.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new AnnotatedClassException("The class " + clazz.getName() + " is annotated with @Packet, but does not extend RedisPacket.");
            }
        }

        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                JSONTokener jsonTokener = new JSONTokener(message);
                JSONObject jsonObject = new JSONObject(jsonTokener);

                for (RedisPacket redisPacket : redisPackets) {
                    String typeOfPacket = redisPacket.getClass().getAnnotation(Packet.class).packetType();
                    if (jsonObject.getString("type") != null && Objects.equals(jsonObject.getString("type"), typeOfPacket)) {
                        redisPacket.onReceive(jsonObject.getJSONObject("data"));
                    }
                }
            }
        }, "stream");
    }
}
