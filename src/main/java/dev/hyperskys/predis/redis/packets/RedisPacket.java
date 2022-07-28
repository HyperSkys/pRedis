package dev.hyperskys.predis.redis.packets;

import org.json.JSONObject;

public abstract class RedisPacket {
    public abstract void onReceive(JSONObject jsonObject);
}
