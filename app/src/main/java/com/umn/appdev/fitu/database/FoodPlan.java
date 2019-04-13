package com.umn.appdev.fitu.database;
import java.io.Serializable;



import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity (tableName = "foodplan")
public class FoodPlan implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "food_name")
    private String name;
/**
    @ColumnInfo(name = "foods")
    private FoodEntry[] foods;
**/

    public FoodPlan(Long id, String name) {
        this.id = id;
        this.name = name;

    }
    @Ignore
    public FoodPlan(String name) {
        this.name = name;

    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    /**
    public FoodEntry[] getFood(){
        return foods;
    }
    public void setFood(FoodEntry[] array){
        this.foods = array;
    }**/
    public Long getId(){ return id; }

}
