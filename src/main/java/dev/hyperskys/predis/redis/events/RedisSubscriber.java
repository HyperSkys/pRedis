package dev.hyperskys.predis.redis.events;

import dev.hyperskys.predis.PRedis;
import dev.hyperskys.predis.redis.exceptions.reflections.AnnotatedClassException;
import dev.hyperskys.predis.redis.exceptions.json.PacketParseException;
import dev.hyperskys.predis.redis.exceptions.reflections.ReflectionsException;
import dev.hyperskys.predis.redis.exceptions.json.UsedValueReturnNull;
import dev.hyperskys.predis.redis.RedisDB;
import dev.hyperskys.predis.redis.packets.RedisPacket;
import dev.hyperskys.predis.redis.packets.annotations.Packet;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.reflections.Reflections;
import redis.clients.jedis.JedisPubSub;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class that is used to set up the Redis Subscriber.
 */
public class RedisSubscriber {

    /**
     * The Jedis Publish & Subscriber instance.
     */
    private static JedisPubSub jedisPubSub;
    private static RedisDB redisDB;

    /**
     * This method is used to instantiate the variables.
     * @param redisDB The RedisDB instance.
     */
    public RedisSubscriber(RedisDB redisDB) {
        RedisSubscriber.redisDB = redisDB;
    }

    public void init() {
        Reflections reflections = new Reflections(PRedis.mainClazz.getPackage().getName());
        ArrayList<RedisPacket> redisPackets = new ArrayList<>();
        for (Class<?> clazz1 : reflections.getTypesAnnotatedWith(Packet.class)) {
            try {
                redisPackets.add((RedisPacket) clazz1.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new AnnotatedClassException("The class " + clazz1.getName() + " is annotated with @Packet, but does not extend RedisPacket.");
            } catch (Exception exception) {
                throw new ReflectionsException("An error occurred while creating an instance of " + clazz1.getName() + ", stack trace: \n" + exception.getMessage());
            }
        }

        jedisPubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                try {
                    JSONTokener jsonTokener = new JSONTokener(message);
                    JSONObject jsonObject = new JSONObject(jsonTokener);

                    for (RedisPacket redisPacket : redisPackets) {
                        String typeOfPacket = redisPacket.getClass().getAnnotation(Packet.class).name();
                        if (jsonObject.getString("type") != null && Objects.equals(jsonObject.getString("type"), typeOfPacket)) {
                            redisPacket.onReceive(jsonObject.getJSONObject("data"));
                        }
                    }
                } catch (JSONException exception) {
                    throw new PacketParseException("Failed to parse the packet to a json object: " + channel + ":" + message);
                } catch (NullPointerException exception) {
                    throw new UsedValueReturnNull("A value in a packet on " + channel + ":" + message + " was used, but a value returned null.");
                }
            }
        };

        new Thread(() -> redisDB.getListenerClient().subscribe(jedisPubSub, "stream"), "Main Redis Thread").start();
    }
}
