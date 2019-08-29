package com.umn.appdev.fitu;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.umn.appdev.fitu.database.AppDatabase;
import com.umn.appdev.fitu.database.FoodEntry;
import com.umn.appdev.fitu.utils.Utils;

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
        FoodEntry check = null;
        for(int i =0; i < 3; i++) {
            mEditText = (EditText) findViewById(id[i]);
            //check if mEditText is not empty
            String sCheck = mEditText.getText().toString();
            if(sCheck.matches("")){
                nutrients[i]= 0;

            }else {
                nutrients[i] = Double.parseDouble(mEditText.getText().toString());
            }
        }
        mEditText = (EditText) findViewById(R.id.name_input);
        name = mEditText.getText().toString();

        //check for duplicates
        check = mDataBase.foodDao().getFood(name);
        if (check != null){

            Toast.makeText(getApplicationContext(), "Not added " + name, Toast.LENGTH_SHORT).show();
        }
        else{

            Toast.makeText(getApplicationContext(), "added " + name, Toast.LENGTH_SHORT).show();
            new LoadIntoDataBase().execute();
            this.finish();


        }

    }

    class LoadIntoDataBase extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            FoodEntry entry = new FoodEntry(name,nutrients[0],nutrients[1],nutrients[2],
                    ++count, Utils.getCurrentDate());

            mDataBase.foodDao().insertFood(entry);
            return null;
        }

    }
}
