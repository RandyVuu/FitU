package com.umn.appdev.fitu.Utils;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;

import com.umn.appdev.fitu.Food_Main;
import com.umn.appdev.fitu.MainActivity;
import com.umn.appdev.fitu.Calendar_Activity;
import com.umn.appdev.fitu.SettingsActivity;
import com.umn.appdev.fitu.R;


public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHelper";

    public static void enableNavigation(final Context context, @NonNull BottomNavigationView mview) {
        mview.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent1 = new Intent(context, MainActivity.class);//ACTIVITY_NUM = 0
                        context.startActivity(intent1);
                        return true;
                    case R.id.navigation_calendar:
                        Intent intent2 = new Intent(context, Calendar_Activity.class);//ACTIVITY_NUM = 1
                        context.startActivity(intent2);
                        return true;
                    case R.id.navigation_settings:
                        Intent intent3 = new Intent(context, SettingsActivity.class);//ACTIVITY_NUM = 2
                        context.startActivity(intent3);
                        return true;
                    case R.id.navigation_food:
                        Intent intent4 = new Intent(context, Food_Main.class);//ACTIVITY_NUM = 3
                        context.startActivity(intent4);
                        return true;
                }
                return false;
            }
        });
    }
}
