package com.umn.appdev.fitu.database;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity (tableName = "foodplan")
public class FoodPlan implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "foods")
    private ArrayList<String> foodList;

    @ColumnInfo(name = "food_name")
    private String name;

    public FoodPlan(Long id, String name, ArrayList<String> foodList) {
        this.id = id;
        this.name = name;
        this.foodList = foodList;
    }
    @Ignore
    public FoodPlan(String name, ArrayList<String> foodList) {
        this.name = name;
        this.foodList= foodList;

    }

    public ArrayList<String> getFoodList() {return foodList; }
    public void setFoodList(ArrayList<String> foodList){ this.foodList = foodList; }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Long getId(){ return id; }

}
