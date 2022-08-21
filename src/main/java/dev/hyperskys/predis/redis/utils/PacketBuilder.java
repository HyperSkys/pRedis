package dev.hyperskys.predis.redis.utils;

import org.json.JSONObject;

import java.util.Collection;
import java.util.HashMap;

/**
 * A utility class for building redis packets.
 */
public class PacketBuilder {

    private final JSONObject jsonObject;
    private final JSONObject dataObject = new JSONObject();

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
     * @param value The number to add to the packet.
     * @return The packet builder.
     */
    public PacketBuilder addData(String key, Number value) {
        dataObject.put(key, value);
        return this;
    }

    /**
     * Adds a key-value pair to the packet.
     * @param key The key to add to the packet.
     * @param value The boolean to add to the packet.
     * @return The packet builder.
     */
    public PacketBuilder addData(String key, boolean value) {
        this.dataObject.put(key, value);
        return this;
    }

    /**
     * Adds a key-value pair to the packet.
     * @param key The key to add to the packet.
     * @param value The collection to add to the packet.
     * @return The packet builder.
     */
    public PacketBuilder addData(String key, Collection<?> value) {
        this.dataObject.put(key, value);
        return this;
    }

    /**
     * Adds a key-value pair to the packet.
     * @param key The key to add to the packet.
     * @param value The hashmap to add to the packet.
     * @return The packet builder.
     */
    public PacketBuilder addData(String key, HashMap<?, ?> value) {
        this.dataObject.put(key, value);
        return this;
    }

    /**
     * Adds a key-value pair to the packet.
     * @param key The key to add to the packet.
     * @param value The object to add to the packet.
     * @return The packet builder.
     */
    public PacketBuilder addData(String key, Object value) {
        this.dataObject.put(key, value);
        return this;
    }

    /**
     * Adds a json object to the packet.
     * @param key The key to add to the packet.
     * @param value The JSON object to add to the packet.
     * @return The packet builder.
     */
    public PacketBuilder addObject(String key, JSONObject value) {
        this.jsonObject.put(key, value);
        return this;
    }

    /**
     * Add a key-value pair to the json object in the packet.
     * @param jsonObject The name of the json object.
     * @param key The key to add to the json object.
     * @param value The string to add to the json object.
     * @return The packet builder.
     */
    public PacketBuilder addDataToObject(String jsonObject, String key, String value) {
        this.jsonObject.getJSONObject(jsonObject).put(key, value);
        return this;
    }

    /**
     * Add a key-value pair to the json object in the packet.
     * @param jsonObject The name of the json object.
     * @param key The key to add to the json object.
     * @param value The number to add to the json object.
     * @return The packet builder.
     */
    public PacketBuilder addDataToObject(String jsonObject, String key, Number value) {
        this.jsonObject.getJSONObject(jsonObject).put(key, value);
        return this;
    }

    /**
     * Add a key-value pair to the json object in the packet.
     * @param jsonObject The name of the json object.
     * @param key The key to add to the json object.
     * @param value The boolean to add to the json object.
     * @return The packet builder.
     */
    public PacketBuilder addDataToObject(String jsonObject, String key, boolean value) {
        this.jsonObject.getJSONObject(jsonObject).put(key, value);
        return this;
    }

    /**
     * Add a key-value pair to the json object in the packet.
     * @param jsonObject The name of the json object.
     * @param key The key to add to the json object.
     * @param value The collection to add to the json object.
     * @return The packet builder.
     */
    public PacketBuilder addDataToObject(String jsonObject, String key, Collection<?> value) {
        this.jsonObject.getJSONObject(jsonObject).put(key, value);
        return this;
    }

    /**
     * Add a key-value pair to the json object in the packet.
     * @param jsonObject The name of the json object.
     * @param key The key to add to the json object.
     * @param value The hashmap to add to the json object.
     * @return The packet builder.
     */
    public PacketBuilder addDataToObject(String jsonObject, String key, HashMap<?, ?> value) {
        this.jsonObject.getJSONObject(jsonObject).put(key, value);
        return this;
    }

    /**
     * Add a key-value pair to the json object in the packet.
     * @param jsonObject The name of the json object.
     * @param key The key to add to the json object.
     * @param value The object to add to the json object.
     * @return The packet builder.
     */
    public PacketBuilder addDataToObject(String jsonObject, String key, Object value) {
        this.jsonObject.getJSONObject(jsonObject).put(key, value);
        return this;
    }

    /**
     * Builds the packet that was set up.
     * @return The packet that was built.
     */
    public JSONObject build() {
        this.jsonObject.put("data", this.dataObject);
        return jsonObject;
    }

    /**
     * Builds the packet and turns it into a string.
     * @return The JSON Object built as a string value.
     */
    public String toString() {
        this.jsonObject.put("data", this.dataObject);
        return jsonObject.toString();
    }
}
