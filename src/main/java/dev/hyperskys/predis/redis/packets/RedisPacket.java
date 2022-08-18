package dev.hyperskys.predis.redis.packets;

import org.json.JSONObject;

/**
 * The base class for all Redis packets.
 */
public abstract class RedisPacket {
    public abstract void onReceive(JSONObject jsonObject);
}
