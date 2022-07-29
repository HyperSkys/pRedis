package dev.hyperskys.predis.redis;

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
    private @Getter @Setter String address;
    private @Getter @Setter int port;
    private @Getter @Setter boolean auth;
    private @Getter @Setter @Nullable String password;
    private @Getter @Setter int timeout;

    public RedisDB(String hostAddress, int hostPort, boolean authentication, @Nullable String hostPassword, int poolTimeout) {
        try {
            setJedis(new Jedis(hostAddress, hostPort, poolTimeout));
            if (authentication) jedis.auth(hostPassword);
            setAddress(hostAddress);
            setPort(hostPort);
            setAuth(authentication);
            setPassword(hostPassword);
            setTimeout(poolTimeout);
            setSender(newClient());
            setListener(newClient());
        } catch (JedisConnectionException exception) {
            throw new RedisConnectionFailedException("Failed to connect to Redis server at " + hostAddress + ":" + hostPort + ", try checking your credentials.");
        }
    }

    public Jedis newClient() {
        Jedis newClient = new Jedis(getAddress(), getPort(), getTimeout());
        if (isAuth()) newClient.auth(getPassword());
        return newClient;
    }

    public void write(JSONObject jsonObject) {
        sender.publish("stream", jsonObject.toString());
    }

    public void close() {
        RedisSubscriber.close();
    }
}
