package com.umn.appdev.fitu.utils;

import android.content.Context;
import android.content.Intent;

import android.view.MenuItem;

import com.umn.appdev.fitu.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.umn.appdev.fitu.CalendarActivity;
import com.umn.appdev.fitu.R;
import com.umn.appdev.fitu.SettingsActivity;
import com.umn.appdev.fitu.FoodActivity;

import androidx.annotation.NonNull;

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    /*private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {*/
    public static void enableNavigation(final Context context, BottomNavigationView mview) {
        mview.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent1 = new Intent(context, MainActivity.class);//ACTIVITY_NUM = 0
                        context.startActivity(intent1);
                        return true;
                    case R.id.navigation_calendar:
                        Intent intent2 = new Intent(context, CalendarActivity.class);//ACTIVITY_NUM = 1
                        context.startActivity(intent2);
                        return true;
                    case R.id.navigation_settings:
                        Intent intent3 = new Intent(context, SettingsActivity.class);//ACTIVITY_NUM = 2
                        context.startActivity(intent3);
                        return true;
                    case R.id.navigation_food:
                        Intent intent4 = new Intent(context, FoodActivity.class);//ACTIVITY_NUM = 3
                        context.startActivity(intent4);
                        return true;
                }
                return false;
            }
        });
    }
}