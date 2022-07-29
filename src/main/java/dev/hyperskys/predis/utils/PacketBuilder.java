package dev.hyperskys.predis.utils;

import org.json.JSONObject;

public class PacketBuilder {
    private static JSONObject jsonObject;
    private static final JSONObject dataObject = new JSONObject();

    public PacketBuilder(String packetType) {
        jsonObject = new JSONObject();
        jsonObject.put("type", packetType);
    }

    public PacketBuilder addData(String key, String value) {
        dataObject.put(key, value);
        return this;
    }

    public PacketBuilder addData(String key, Number number) {
        dataObject.put(key, number);
        return this;
    }

    public PacketBuilder addData(String key, boolean value) {
        dataObject.put(key, value);
        return this;
    }

    public JSONObject build() {
        jsonObject.put("data", dataObject);
        return jsonObject;
    }
}
