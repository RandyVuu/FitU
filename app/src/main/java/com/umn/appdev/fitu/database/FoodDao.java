package com.umn.appdev.fitu.database;

import java.util.Date;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface FoodDao {
    @Query("SELECT * FROM food WHERE date = :curDate ORDER BY priority ")
    List<FoodEntry> loadAllFoods(Date curDate);

    @Query("SELECT * FROM food WHERE food_name = :fName LIMIT 1 ")
    FoodEntry getFood(String fName);

    @Insert
    void insertFood(FoodEntry foodEntry);

    @Update
    void updateFood(FoodEntry foodEntry);

    @Delete
    void deleteFood(FoodEntry foodEntry);
}
