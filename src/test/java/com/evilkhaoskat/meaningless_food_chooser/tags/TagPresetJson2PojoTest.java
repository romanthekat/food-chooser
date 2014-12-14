package com.evilkhaoskat.meaningless_food_chooser.tags;

import com.evilkhaoskat.meaningless_food_chooser.tag.TagPreset;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by evilkhaoskat on 14.12.14.
 */
public class TagPresetJson2PojoTest {

    @Test
    public void testTagPresetJson2Pojo() throws IOException {
        String tagPresetName = "AwesomePreset";
        List<String> mustUseTags = new ArrayList<>(2);
        mustUseTags.add("use1");
        mustUseTags.add("use2");
        List<String> musNotUseTags = new ArrayList<>(2);
        musNotUseTags.add("DoNotUse1");
        musNotUseTags.add("DoNotUse2");

        TagPreset testTagPreset = new TagPreset();
        testTagPreset.setTagPresetName(tagPresetName);
        testTagPreset.setMustBeTags(mustUseTags);
        testTagPreset.setMustNotBeTags(musNotUseTags);

        //I don't like hardcode, but extra variables for 'use1', 'use2', etc looks even more strange that that hardcode
        String json = "{\"tagPresetName\" : \"AwesomePreset\", " +
                "\"mustBeTags\" : [\"use1\", \"use2\"], " +
                "\"mustNotBeTags\" : [\"DoNotUse1\", \"DoNotUse2\"]}";
        TagPreset jsonCreatedTagPreset = TagPreset.getTagPresetByJSON(json);

        assertEquals(testTagPreset, jsonCreatedTagPreset);
    }
}
