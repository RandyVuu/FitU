package com.umn.appdev.fitu;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
        Context context = this;
        /**
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            startActivity(new Intent(this,FoodPlanActivity.class));
        }}
        **/
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        }
        public void gotoFood(View v){
        Intent i = new Intent(MainActivity.this, FoodListActivity.class);
        startActivity(i);
        }
        public void gotoFoodPlan(View v){
        Intent i = new Intent(MainActivity.this, FoodPlanActivity.class);
        startActivity(i);

        }

}
