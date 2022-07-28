package dev.hyperskys.predis.example;

import dev.hyperskys.predis.redis.packets.RedisPacket;
import dev.hyperskys.predis.redis.packets.annotations.Packet;
import org.json.JSONObject;

@Packet(packetType = "broadcast")
public class BroadcastPacket extends RedisPacket {
    @Override
    public void onReceive(JSONObject jsonObject) {
        System.out.println(jsonObject.getString("message"));
    }
}
