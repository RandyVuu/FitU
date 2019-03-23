package com.umn.appdev.fitu.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "food")
public class FoodEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int priority;
    @ColumnInfo(name = "food_name")
    private String name;
    @ColumnInfo(name = "protein")
    private double protein;
    @ColumnInfo(name = "fats")
    private double fats;
    @ColumnInfo(name = "carbs")
    private double carbs;
    @ColumnInfo(name = "calories")
    private double calories;
    public FoodEntry(int id, String name, double carbs, double fats, double protein, double calories,
                     int priority) {
        this.id = id;
        this.priority = priority;
        this.name = name;
        this.carbs = carbs;
        this.fats = fats;
        this.protein = protein;
        this.calories = calories;
    }
    @Ignore
    public FoodEntry(String name, double carbs, double fats, double protein, int priority) {
        this.priority = priority;
        this.name = name;
        this.carbs = carbs;
        this.fats = fats;
        this.protein = protein;
        this.calories = (4*carbs) + (9*fats) + (4*protein);

    }
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public double getCarbs(){return carbs;}
    public void setCarbs(double carb){this.carbs = carb; this.calories = (4*carbs) + (9*fats) + (4*protein); }
    public double getProtein(){return protein;}
    public void setProtein(double protein){this.protein = protein; this.calories = (4*carbs) + (9*fats) + (4*protein); }
    public double getFats(){return fats;}
    public void setFats(double fats){{this.fats = fats; this.calories = (4*carbs) + (9*fats) + (4*protein); }}
    public int getPriority(){return priority;}
    public void setPriority(int priority){this.priority = priority;}

}
