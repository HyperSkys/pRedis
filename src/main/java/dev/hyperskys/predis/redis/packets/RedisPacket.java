package dev.hyperskys.predis.redis.packets;

import org.json.JSONObject;

/**
 * The base class for all Redis packets.
 */
public abstract class RedisPacket {
    /**
     * The method that is called when a packet is received.
     * @param jsonObject The JSONObject that was received.
     */
    public abstract void onReceive(JSONObject jsonObject);
}
