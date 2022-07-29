package dev.hyperskys.predis.utils;

import org.json.JSONObject;

/**
 * A utility class for building redis packets.
 */
public class PacketBuilder {
    private static JSONObject jsonObject;
    private static final JSONObject dataObject = new JSONObject();

    /**
     * Creates a new packet builder.
     * @param packetType The type of packet to create.
     */
    public PacketBuilder(String packetType) {
        jsonObject = new JSONObject();
        jsonObject.put("type", packetType);
    }

    /**
     * Adds a key-value pair to the packet.
     * @param key The key to add to the packet.
     * @param value The string to add to the packet.
     * @return The packet builder.
     */
    public PacketBuilder addData(String key, String value) {
        dataObject.put(key, value);
        return this;
    }

    /**
     * Adds a key-value pair to the packet.
     * @param key The key to add to the packet.
     * @param number The number to add to the packet.
     * @return The packet builder.
     */
    public PacketBuilder addData(String key, Number number) {
        dataObject.put(key, number);
        return this;
    }

    /**
     * Adds a key-value pair to the packet.
     * @param key The key to add to the packet.
     * @param value The boolean to add to the packet.
     * @return The packet builder.
     */
    public PacketBuilder addData(String key, boolean value) {
        dataObject.put(key, value);
        return this;
    }

    /**
     * Builds the packet that was set up.
     * @return The packet that was built.
     */
    public JSONObject build() {
        jsonObject.put("data", dataObject);
        return jsonObject;
    }
}
