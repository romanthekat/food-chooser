package com.evilkhaoskat.meaningless_food_chooser.dish;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.evilkhaoskat.meaningless_food_chooser.dish.Dish.getDishDBDishPojoMapper;

/**
 * Created by evilkhaoskat on 13.12.14.
 */
public class DishHelper {

    private static Logger log = Logger.getLogger(DishHelper.class.getName());
    private DishHelper() {}

    public static final String DISHES_DB = "dishes";
    public static final String DISHES_COLLECTION = "dishes";


    public static List<Dish> getAllDishes() {
        try {
            DB dishesDb = getDB();
            return getDishesByDB(dishesDb);
        } catch (UnknownHostException e) {
            log.log(Level.SEVERE, "mongo client cannot be obtained, " + e);
        }

        return Collections.emptyList();
    }

    //yeah, that would be cool to use some ORM/ODM like mongolink or morphia
    //but that leads to zero using of mongodb itself in that situation
    //whole project was made for using several technologies at least somehow
    private static List<Dish> getDishesByDB(DB dishesDb) {
        DBCollection dishesDBCollection = dishesDb.getCollection(DISHES_COLLECTION);
        DBCursor dishesCursor = dishesDBCollection.find();

        List<DBObject> dishesDBObjects = dishesCursor.toArray();
        return dishesDBObjects.stream().map(getDishDBDishPojoMapper()).collect(Collectors.toList());
    }

    protected static DB getDB() throws UnknownHostException {
        MongoClient mongoClient = getMongoClient();
        return mongoClient.getDB(DISHES_DB);
    }

    //TODO mongo server address to be specifiable
    protected static MongoClient getMongoClient() throws UnknownHostException {
        return new MongoClient();
    }
}
