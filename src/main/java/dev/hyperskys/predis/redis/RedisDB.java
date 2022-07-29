package dev.hyperskys.predis.redis;

import dev.hyperskys.predis.exceptions.RedisConnectionFailedException;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.annotation.Nullable;

public class RedisDB {

    private @Getter @Setter Jedis jedis;

    public RedisDB(String hostAddress, int hostPort, boolean authentication, @Nullable String hostPassword, int poolTimeout) {
        try {
            setJedis(new Jedis(hostAddress, hostPort, poolTimeout));
            if (authentication) jedis.auth(hostPassword);
        } catch (JedisConnectionException exception) {
            throw new RedisConnectionFailedException("Failed to connect to Redis server at " + hostAddress + ":" + hostPort + ", try checking your credentials.");
        }
    }

    public void write(JSONObject jsonObject) {
        jedis.publish("stream", jsonObject.toString());
    }

    public void close() {
        jedis.close();
    }
}
