package com.umn.appdev.fitu;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.umn.appdev.fitu.database.AppDatabase;
import com.umn.appdev.fitu.database.FoodEntry;
import com.umn.appdev.fitu.database.FoodPlan;
import com.umn.appdev.fitu.utils.Utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class AddFoodPlan extends AppCompatActivity {
    static int count = 0;
    String name;

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

    class LoadIntoDataBase extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            FoodPlan plan = new FoodPlan(name);
            mDataBase.planDao().insert(plan);
            return null;
        }

    }
}
