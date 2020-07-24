package com.umn.appdev.fitu.database;

import java.io.Serializable;
import java.util.Date;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "food")
public class FoodEntry implements Serializable {
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
    @ColumnInfo(name = "date")
    private Date date;

    public FoodEntry(int id, String name, double carbs, double fats, double protein, double calories,
                     int priority, Date date) {
        this.id = id;
        this.priority = priority;
        this.name = name;
        this.carbs = carbs;
        this.fats = fats;
        this.protein = protein;
        this.calories = calories;
        this.date = date;
    }
    @Ignore
    public FoodEntry(String name, double protein, double carbs, double fats, int priority, Date date) {
        this.priority = priority;
        this.name = name;
        this.carbs = carbs;
        this.fats = fats;
        this.protein = protein;
        this.calories = (4*carbs) + (9*fats) + (4*protein);
        this.date = date;

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
    public double getCalories(){return calories;}
    public Date getDate(){return date;}
    public int getId(){return id;}
}
