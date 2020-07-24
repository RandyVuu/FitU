package com.umn.appdev.fitu.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {FoodEntry.class, CalendarEntry.class, FoodPlan.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class, Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;
    private static final String DB_NAME = "app.db";
    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null){
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DB_NAME)
                            .allowMainThreadQueries() //SHOULD NOT BE USED
                            .addCallback(new RoomDatabase.Callback(){
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db){
                                    super.onCreate(db);
                                    Log.d("app database", "database operational");

                                    new PopulateDbAsync(sInstance).execute();
                                }
                            }).build();
                }

            }}
        return sInstance;
    }

    public abstract CalendarDao CalendarDao();
    public abstract PlanDao planDao();
    public abstract FoodDao foodDao();

    public void clearDb(){
        if(sInstance != null){
            new PopulateDbAsync(sInstance).execute();
        }
    }
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private final CalendarDao calendarDao;
        private final PlanDao planDao;
        private final FoodDao foodDao;
        public PopulateDbAsync(AppDatabase instance){
            calendarDao = instance.CalendarDao();
            planDao = instance.planDao();
            foodDao = instance.foodDao();
        }
        @Override
        protected Void doInBackground(Void... voids){
            return null;

        }

    }

}

