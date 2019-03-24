package com.umn.appdev.fitu;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.umn.appdev.fitu.database.AppDatabase;
import com.umn.appdev.fitu.database.FoodEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class AddFoodActivity extends AppCompatActivity {
    static int count = 0;
    String name;
    double[] nutrients = new double[3];
    int[] id;
    private AppDatabase mDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        id = new int[]{R.id.protein_input, R.id.carbs_input , R.id.fat_input};
        mDataBase = AppDatabase.getInstance(getApplicationContext());
    }
    public void onSubmit(View view) {
        EditText mEditText;
        for(int i =0; i < 3; i++) {
            mEditText = (EditText) findViewById(id[i]);
            nutrients[i] = Double.parseDouble(mEditText.getText().toString());
        }
        mEditText = (EditText) findViewById(R.id.name_input);
        name = mEditText.getText().toString();
        new LoadIntoDataBase().execute();
        this.finish();
    }
    private Date getCurrentDate() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }
    class LoadIntoDataBase extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            FoodEntry entry = new FoodEntry(name,nutrients[0],nutrients[1],nutrients[2],
                    ++count,getCurrentDate());
            mDataBase.foodDao().insertFood(entry);
            return null;
        }

    }
}
