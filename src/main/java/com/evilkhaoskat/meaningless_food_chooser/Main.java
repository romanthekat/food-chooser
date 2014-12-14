package com.evilkhaoskat.meaningless_food_chooser;

import com.evilkhaoskat.meaningless_food_chooser.dish.DishHelper;
import com.evilkhaoskat.meaningless_food_chooser.tag.TagHelper;

public class Main
{
    //TODO set up logger
    //TODO set up settings: db connections, initial values if there are no records (default 'Dishes' and 'TagPresets')
    public static void main( String[] args ) {
        System.out.println("allDishes:" + DishHelper.getAllDishes());
        System.out.println("allTagPresets:" + TagHelper.getAllTagPresets());
    }
}
