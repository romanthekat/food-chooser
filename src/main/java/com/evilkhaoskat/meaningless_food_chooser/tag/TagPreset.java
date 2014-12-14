package com.evilkhaoskat.meaningless_food_chooser.tag;

import org.codehaus.jackson.map.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by evilkhaoskat on 14.12.14.
 */
public class TagPreset {
    private static Logger log = Logger.getLogger(TagPreset.class.getName());

    //public static enum TagType{MUST_BE, MUST_NOT_BE}

    protected String tagPresetName = "";

    protected List<String> mustBeTags;
    protected List<String> mustNotBeTags;

    public String getTagPresetName() {
        return tagPresetName;
    }

    public void setTagPresetName(String tagPresetName) {
        this.tagPresetName = tagPresetName;
    }

    public List<String> getMustBeTags() {
        return mustBeTags;
    }

    public void setMustBeTags(List<String> mustBeTags) {
        this.mustBeTags = mustBeTags;
    }

    public List<String> getMustNotBeTags() {
        return mustNotBeTags;
    }

    public void setMustNotBeTags(List<String> mustNotBeTags) {
        this.mustNotBeTags = mustNotBeTags;
    }

    public static Function<String, TagPreset> getRedis2PojoTagPresetMapper(Jedis jedis) {
        return redisKey ->
        {
            try {
                return getTagPresetByJSON(jedis.get(redisKey));
            } catch (IOException e) {
                log.log(Level.SEVERE, "IOException during json mapping:" + e);
            }

            return null;
        };
    }

    public static TagPreset getTagPresetByJSON(String json) throws IOException {
        System.out.println("input json:" + json);
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.readValue(json, TagPreset.class);
    }

    @Override
    public String toString() {
        return "TagPreset{" +
                "tagPresetName='" + tagPresetName + '\'' +
                ", mustBeTags=" + mustBeTags +
                ", mustNotBeTags=" + mustNotBeTags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagPreset tagPreset = (TagPreset) o;

        if (!mustBeTags.equals(tagPreset.mustBeTags)) return false;
        if (!mustNotBeTags.equals(tagPreset.mustNotBeTags)) return false;
        if (!tagPresetName.equals(tagPreset.tagPresetName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagPresetName.hashCode();
        result = 31 * result + mustBeTags.hashCode();
        result = 31 * result + mustNotBeTags.hashCode();
        return result;
    }
}
