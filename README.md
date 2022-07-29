# pRedis [v1.0.5-STABLE]

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![GitHub](https://img.shields.io/github/languages/code-size/HyperSkys/pRedis?color=cyan&label=Size&labelColor=000000&logo=GitHub&style=for-the-badge)
![GitHub](https://img.shields.io/github/license/HyperSkys/pRedis?color=violet&logo=GitHub&labelColor=000000&style=for-the-badge)
![Discord](https://img.shields.io/discord/898154272636678196?color=5865F2&label=Discord&logo=Discord&labelColor=23272a&style=for-the-badge)

**pRedis** is a universe Redis library for Java, which is open to the public.

### Download Instructions

To download and use pRedis go to the releases tab download the pRedis.jar file and add it to your artifacts or dependencies, via the JitPack repository.

### API Introduction

Below is an example of how you would create a packet that you would listen for.
```java
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
```

Below is an example of how you would send a broadcast packet.
```java
import dev.hyperskys.predis.redis.RedisDB;
import dev.hyperskys.predis.utils.PacketBuilder;

public class PacketSender {
    
    private static final RedisDB redisDB = new RedisDB(
            "localhost", 
            6379, 
            false, 
            null, 
            5000
    );
    
    public static void main(String[] args) {
        PRedis.init(PacketSender.class, redisDB);
        redisDB.write(new PacketBuilder("broadcast").addData("message", "Hello, World!").build());
    }
}
```

### Project Intergration

There are two major different types of ways of integrating this API into your project, below will list the way of integrating while using Maven and Gradle.


#### Maven
For maven please add these to your repositories and dependencies.
```
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>
``` 
```
 <dependency>
    <groupId>com.github.HyperSkys</groupId>
    <artifactId>pRedis</artifactId>
    <version>1.0.5-STABLE</version>
</dependency>
```

-----------------------

#### Gradle
For gradle add this to your repositories and dependencies.
```
maven { url 'https://jitpack.io' }
```
```
implementation 'com.github.HyperSkys:Configurator:1.0.5-STABLE'
```

### Discord

You can join my discord using this invite https://discord.gg/Y59DddqZZR please join the server if you have any issues or suggestions that you would like to make do not make random issues when you could just use the discord.

## License
This project is licensed under [Eclipse Public License](https://github.com/HyperSkys/pRedis/blob/main/LICENSE)