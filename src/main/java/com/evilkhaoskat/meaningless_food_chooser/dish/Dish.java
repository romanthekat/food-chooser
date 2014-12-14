package com.evilkhaoskat.meaningless_food_chooser.dish;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by evilkhaoskat on 13.12.14.
 */
public class Dish {
    String id = "";
    public static final String ID = "_id";

    String name = "";
    public static final String DISH_NAME = "name";

    String description = "";
    public static final String DISH_DESCRIPTION = "description";

    List<String> tags = Collections.emptyList();
    public static final String DISH_TAGS = "tags";

    public Dish() {}

    public Dish(String id, String name, String description, List<String> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tags = tags;
    }

    public static Function<DBObject, Dish> getDishDBDishPojoMapper() {
        return x ->
        {
            ObjectId objectId = (ObjectId)x.get(ID);

            BasicDBList rawTags = (BasicDBList) x.get(DISH_TAGS);
            List<String> tags = rawTags != null ?
                    rawTags.stream().map(Object::toString).collect(Collectors.toList()) :
                    Collections.emptyList();

            return new Dish(objectId.toString(),
                    (String)x.get(DISH_NAME),
                    (String)x.get(DISH_DESCRIPTION),
                    tags);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (!description.equals(dish.description)) return false;
        if (!id.equals(dish.id)) return false;
        if (!name.equals(dish.name)) return false;
        if (!tags.equals(dish.tags)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + tags.hashCode();
        return result;
    }
}
