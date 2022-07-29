package dev.hyperskys.predis.redis;

import dev.hyperskys.predis.redis.exceptions.json.NullParameterException;
import dev.hyperskys.predis.redis.exceptions.redis.PublishException;
import dev.hyperskys.predis.redis.exceptions.redis.RedisConnectionFailedException;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.annotation.Nullable;

/**
 * RedisDB is a wrapper class for Redis.
 */
public class RedisDB {

    private @Getter @Setter Jedis listenerClient;
    private @Getter @Setter Jedis senderClient;

    /**
     * Initializes the RedisDB.
     * @param hostAddress The host address of the Redis server, e.g. "localhost".
     * @param hostPort The host port of the Redis server, default is 6379.
     * @param authentication If authentication is enabled on the Redis server.
     * @param hostPassword The host password of the Redis server, leave null if authentication false.
     * @param poolTimeout The pool timeout of the Redis server, advised timeout is 5000.
     */
    public RedisDB(String hostAddress, int hostPort, boolean authentication, @Nullable String hostPassword, int poolTimeout) {
        try {
            setSenderClient(new Jedis(hostAddress, hostPort, poolTimeout));
            setListenerClient(new Jedis(hostAddress, hostPort, poolTimeout));

            if (authentication) {
                listenerClient.auth(hostPassword);
                senderClient.auth(hostPassword);
            }
        } catch (JedisConnectionException exception) {
            throw new RedisConnectionFailedException("Failed to connect to Redis server at " + hostAddress + ":" + hostPort + ", try checking your credentials.");
        } catch (Exception exception) {
            throw new NullParameterException("Failed to connect to Redis server at " + hostAddress + ":" + hostPort + ", one of the parameters is null.");
        }
    }

    /**
     * Publishes a json object to the stream channel.
     * @param jsonObject The json object to publish to stream.
     */
    public void write(JSONObject jsonObject) {
        try {
            senderClient.publish("stream", jsonObject.toString());
        } catch (Exception exception) {
            throw new PublishException("Failed to publish to Redis server, the JSON object must be null, or the Redis server is not running.");
        }
    }
}
