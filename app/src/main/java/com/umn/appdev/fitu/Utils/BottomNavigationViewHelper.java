package com.fitU.fitu.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.fitU.fitu.MainActivity;
import com.fitU.fitu.CalendarActivity;
import com.fitU.fitu.SettingsActivity;
import com.fitU.fitu.FoodActivity;
import com.fitU.fitu.R;


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