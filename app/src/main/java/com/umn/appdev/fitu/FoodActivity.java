package com.umn.appdev.fitu;

import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.umn.appdev.fitu.R;
import com.umn.appdev.fitu.utils.BottomNavigationViewHelper;

import androidx.appcompat.app.AppCompatActivity;
//what is this class for?
public class FoodActivity extends AppCompatActivity {

    //private static final String TAG = "FoodActivity";
    //private static final int ACTIVITY_NUM = 2;

    private Context mContext = FoodActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setupBottomNavigationView();
    }
    /**
    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.enableNavigation(mContext, navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
    **/
}
