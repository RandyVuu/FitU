package com.umn.appdev.fitu;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
        Context context = this;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            startActivity(new Intent(this,FoodPlanActivity.class));
        }
    /**      }
            public void onClick(View v){
              Intent i = new Intent(MainActivity.this, Calendar_Activity.class);
              startActivity(i);*
      }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, FoodListActivity.class);
        startActivity(intent);
    }
    **/
}
