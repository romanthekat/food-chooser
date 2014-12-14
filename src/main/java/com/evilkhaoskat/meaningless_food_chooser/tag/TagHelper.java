package com.evilkhaoskat.meaningless_food_chooser.tag;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by evilkhaoskat on 14.12.14.
 */
public class TagHelper {
    protected TagHelper() {}

    //yeah, that would be cool to use some OHM like johm
    //but that leads to zero using of redis itself in that situation
    //whole project was made for using several technologies at least somehow
    public static List<TagPreset> getAllTagPresets() {
        Jedis jedis = getJedisConnection();
        Set<String> presetsNames = jedis.keys("*"); //TODO a bit strange way of getting all keys. are there any more adequate ways?
        return presetsNames.stream().map(TagPreset.getRedis2PojoTagPresetMapper(jedis)).collect(Collectors.toList());
    }

    //TODO redis server address to be specifiable
    private static Jedis getJedisConnection() {
        return new Jedis("localhost");
    }
}
