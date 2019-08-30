package com.umn.appdev.fitu.database;

import java.util.Date;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CalendarDao {
    @Query("SELECT * FROM calendar WHERE date = :curDate ORDER BY priority ")
    List<CalendarEntry> loadAllFoods(Date curDate);

    @Query("SELECT * FROM calendar ORDER BY priority")
    List<CalendarEntry> loadEverything();

    @Query("SELECT * FROM calendar WHERE food_name = :fName LIMIT 1 ")
    CalendarEntry getFood(String fName);

    @Insert
    void insertFood(CalendarEntry calendarEntry);

    @Update
    void updateFood(CalendarEntry calendarEntry);

    @Delete
    void deleteFood(CalendarEntry calendarEntry);
}
