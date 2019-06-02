package com.umn.appdev.fitu.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PlanDao {
    @Query("SELECT * FROM foodplan")
    public List<FoodPlan> getFoodPlan();

    @Query("SELECT * FROM foodplan WHERE id = :id")
    public FoodPlan getItemByID(Long id);

    @Insert
    public void insert(FoodPlan Foodplans);
    @Update
    public void update(FoodPlan Foodplans);
    @Delete
    public void delete(FoodPlan Foodplans);

}