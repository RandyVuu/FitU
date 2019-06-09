package com.umn.appdev.fitu;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.umn.appdev.fitu.database.AppDatabase;
import com.umn.appdev.fitu.database.FoodEntry;
import com.umn.appdev.fitu.database.FoodPlan;
import com.umn.appdev.fitu.utils.Utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class AddFoodPlan extends AppCompatActivity {
    static int count = 0;
    String name;
    ArrayList<String> foodLists = new ArrayList<String>();

    private AppDatabase mDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);

        mDataBase = AppDatabase.getInstance(getApplicationContext());
    }
    public void onSubmit(View view) {
        EditText mEditText;
        mEditText = (EditText) findViewById(R.id.name_input);
        name = mEditText.getText().toString();
        new LoadIntoDataBase().execute();
        this.finish();
    }
    /** Add food name to foodplan array**/
    public void onAdd(View view){
        EditText mEditText;
        mEditText = (EditText) findViewById(R.id.FoodText);
        name = mEditText.getText().toString();
        count++;
        //check if food is in foodentry
        FoodEntry check = null;
        check = mDataBase.foodDao().getFood(name);
        //show if food is added or not
        foodLists.add(name);
        if (foodLists.contains(name) && (check != null)){
            Toast.makeText(getApplicationContext(), "added " + name, Toast.LENGTH_SHORT).show();

        }
        else{
            //food is not in food database
            Toast.makeText(getApplicationContext(), "Not added " + name, Toast.LENGTH_SHORT).show();

        }
        return;
    }

    class LoadIntoDataBase extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            FoodPlan plan = new FoodPlan(name, foodLists);
            mDataBase.planDao().insert(plan);
            return null;
        }

    }
}
