package com.umn.appdev.fitu;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.umn.appdev.fitu.utils.BottomNavigationViewHelper;

public class Food_Main extends AppCompatActivity {
    private static final int ACTIVITY_NUM = 2;
    private static final String TAG = "Food_Main";
    Context mContext = Food_Main.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_main);
        setupBottomNavigationView();

    }
    public void gotoFood(View v){
        Intent i = new Intent(Food_Main.this, FoodListActivity.class);
        startActivity(i);
    }
    public void gotoFoodPlan(View v){
        Intent i = new Intent(Food_Main.this, FoodPlanActivity.class);
        startActivity(i);

    }
    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.enableNavigation(mContext, navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
