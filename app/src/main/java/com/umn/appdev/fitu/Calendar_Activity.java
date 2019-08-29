
package com.umn.appdev.fitu;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;

import com.umn.appdev.fitu.Utils.BottomNavigationViewHelper;

import java.util.Calendar;

public class Calendar_Activity extends AppCompatActivity {

	private static final String TAG = "CalendarActivity";
    private static final int ACTIVITY_NUM = 1;

    private static final int NUM_LIST_ITEMS = 30;
    private CalendarView mCalendarView;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_);
        setupBottomNavigationView();
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Intent intent = new Intent(Calendar_Activity.this,FoodListActivity.class);
                intent.putExtra("date", dayOfMonth + "/" + month + "/" + year);
                startActivity(intent);
            }
        });
    }



	private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.enableNavigation(context, navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
