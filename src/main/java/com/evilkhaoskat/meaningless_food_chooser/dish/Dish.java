package com.evilkhaoskat.meaningless_food_chooser.dish;

import com.mongodb.DBObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * Created by evilkhaoskat on 13.12.14.
 */
public class Dish {
    String name;
    public static final String DISH_NAME = "name";

    String description;
    public static final String DISH_DESCRIPTION = "description";

    List<String> tags;
    public static final String DISH_TAGS = "tags";

    public Dish() {}

    public Dish(String name, String description, List<String> tags) {
        this.name = name;
        this.description = description;
        this.tags = tags;
    }

    public static Function<DBObject, Dish> getDishDBDishPojoMapper() {
        return x ->
        {
            String rawTags = (String) x.get(DISH_TAGS);
            List<String> tags = Arrays.asList(rawTags.split(","));

            return new Dish((String)x.get(DISH_NAME), (String)x.get(DISH_DESCRIPTION), tags);
        };
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
