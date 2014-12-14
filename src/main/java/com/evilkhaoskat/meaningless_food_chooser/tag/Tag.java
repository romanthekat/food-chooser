package com.evilkhaoskat.meaningless_food_chooser.tag;

/**
 * Created by evilkhaoskat on 14.12.14.
 */
@Deprecated
public class Tag {

    protected String name = "";

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                '}';
    }
}
