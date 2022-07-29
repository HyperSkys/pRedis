package dev.hyperskys.predis.redis;

import dev.hyperskys.predis.PRedis;
import dev.hyperskys.predis.exceptions.NullParameterException;
import dev.hyperskys.predis.exceptions.PublishException;
import dev.hyperskys.predis.exceptions.RedisConnectionFailedException;
import dev.hyperskys.predis.redis.events.RedisSubscriber;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.annotation.Nullable;

public class RedisDB {

    private @Getter @Setter Jedis jedis;
    private @Getter @Setter Jedis listener;
    private @Getter @Setter Jedis sender;

    public RedisDB(String hostAddress, int hostPort, boolean authentication, @Nullable String hostPassword, int poolTimeout) {
        try {
            setJedis(new Jedis(hostAddress, hostPort, poolTimeout));
            setSender(new Jedis(hostAddress, hostPort, poolTimeout));
            setListener(new Jedis(hostAddress, hostPort, poolTimeout));

            if (authentication) {
                jedis.auth(hostPassword);
                listener.auth(hostPassword);
                sender.auth(hostPassword);
            }
        } catch (JedisConnectionException exception) {
            throw new RedisConnectionFailedException("Failed to connect to Redis server at " + hostAddress + ":" + hostPort + ", try checking your credentials.");
        } catch (Exception exception) {
            throw new NullParameterException("Failed to connect to Redis server at " + hostAddress + ":" + hostPort + ", one of the parameters is null.");
        }
    }

    public void write(JSONObject jsonObject) {
        try {
            sender.publish("stream", jsonObject.toString());
        } catch (Exception exception) {
            throw new PublishException("Failed to publish to Redis server, the JSON object must be null, or the Redis server is not running.");
        }
    }

    public void close() {
        PRedis.getRedisSubscriber().close();
    }
}
