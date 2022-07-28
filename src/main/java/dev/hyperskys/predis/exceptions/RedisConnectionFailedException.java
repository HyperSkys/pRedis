package dev.hyperskys.predis.exceptions;

public class RedisConnectionFailedException extends RuntimeException{
    public RedisConnectionFailedException(String reason) {
        super(reason);
    }
}
