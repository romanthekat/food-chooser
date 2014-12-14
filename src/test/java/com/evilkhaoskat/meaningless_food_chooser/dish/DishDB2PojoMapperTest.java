package com.evilkhaoskat.meaningless_food_chooser.dish;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by evilkhaoskat on 13.12.14.
 */
public class DishDB2PojoMapperTest {

    @Test
    public void testDishDB2PojoMapper() {
        String dishId = "000000000000000000000000"; //'valid' object id for ObjectId initiation. looks awful. TODO make smth with it
        String dishName = "Awesome dish";
        String dishDescription = "Made of awesomeness!";
        //String dishTags = "awesome,cool,great";
        BasicDBList dishTagsDBList = new BasicDBList();
        dishTagsDBList.add("awesome");
        dishTagsDBList.add("cool");
        dishTagsDBList.add("great");

        DBObject dbDishObject = mock(DBObject.class);
        when(dbDishObject.get(Dish.ID)).thenReturn(new ObjectId(dishId));
        when(dbDishObject.get(Dish.DISH_NAME)).thenReturn(dishName);
        when(dbDishObject.get(Dish.DISH_DESCRIPTION)).thenReturn(dishDescription);
        when(dbDishObject.get(Dish.DISH_TAGS)).thenReturn(dishTagsDBList);

        //TODO can we use 'functional interface' directly to transform DBObject->Dish pojo ?
        List<Dish> dishes = Collections.singletonList(dbDishObject).
                stream().
                map(Dish.getDishDBDishPojoMapper()).
                collect(Collectors.toList());

        Dish dish = dishes.get(0);

        //TODO use equals between exemplars of objects?
        assertEquals(dish.getName(), dishName);
        assertEquals(dish.getDescription(), dishDescription);

        List<String> dishTagsList = new ArrayList<>(3);
        dishTagsList.add("awesome");
        dishTagsList.add("cool");
        dishTagsList.add("great");
        assertEquals(dish.getTags(), dishTagsList);
    }
}
