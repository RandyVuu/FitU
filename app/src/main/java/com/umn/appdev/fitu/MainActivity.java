package com.umn.appdev.fitu;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.umn.appdev.fitu.Utils.BottomNavigationViewHelper;
import com.umn.appdev.fitu.Utils.Utils;
import com.umn.appdev.fitu.database.AppDatabase;
import com.umn.appdev.fitu.database.FoodEntry;

import java.util.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static AppDatabase entry;
    private static final String TAG = "MainActivity";
    private static final int ACTIVITY_NUM = 0;

    private Context mContext = MainActivity.this;
    //private PieChart mchart;
    private BarChart mchart;
    private String foodname;
    private double cal =0;
    private double protein =0;
    private double fats =0;
    private double carbs =0;
    private String val;
    EditText foodcalname;
    EditText numInPro;
    EditText numInFat;
    EditText numInCab;
    Button submitButton;
    List <FoodEntry> entries;
    double[] nutrients = new double[3];

    private AppDatabase mDataBase;

    ArrayList<PieEntry> values = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        double[] dnutrients = new double[4];

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBottomNavigationView();

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.meals, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        mchart = (BarChart) findViewById(R.id.chart1);
        mchart.setBackgroundColor(Color.TRANSPARENT);
        mchart.setMaxVisibleValueCount(40);

        foodcalname = (EditText) findViewById(R.id.foodid);
        numInPro = (EditText) findViewById(R.id.numpro);
        numInFat = (EditText) findViewById(R.id.numfat);
        numInCab = (EditText) findViewById(R.id.numcarbs);

        submitButton = (Button) findViewById(R.id.button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!foodcalname.getText().toString().isEmpty()){foodname = foodcalname.getText().toString();}
                if(!numInPro.getText().toString().isEmpty()){nutrients[0] = Integer.valueOf(numInPro.getText().toString());}
                if(!numInFat.getText().toString().isEmpty()){nutrients[2] = Integer.valueOf(numInFat.getText().toString());}
                if(!numInCab.getText().toString().isEmpty()){nutrients[1] = Integer.valueOf(numInCab.getText().toString());}
                /*if(cal == 0) {
                    cal = (4*carbs) + (9*fats) + (4*protein);
                }
                // generates graph by reading global cal and the current cal values in the graph already ?????
                //setData(10);

                //retreive list of the entries that is related to the current date

                //resets the global cal to 0
                cal =0;*/
                new LoadIntoDataBase().execute();
            }
        });
        //Load all food entry from current date into an array list


        new LoadFromDataBase().execute();

        if(entries == null||entries.size() == 0){
            new LoadFromDataBase2().execute();
        }
        //loop through lsit of entries and add the information for the current date into vals and update graph
        if(entries != null) {
            for (FoodEntry fde : entries) {

                protein += fde.getProtein();
                carbs += fde.getCarbs();
                fats += fde.getFats();
                cal += fde.getCalories();

            }
        }

        dnutrients[0] = protein;
        dnutrients[1] = carbs;
        dnutrients[2] = fats;
        dnutrients[3] = cal;

        setData(dnutrients);

    }

   /* private void setData(){
        PieDataSet dataSet;
        if(!values.isEmpty()){
            ArrayList<PieEntry> newvalues = new ArrayList<>();
            boolean checks = false;
            for(PieEntry x : values) {
                if (x.getLabel().equals(val)) {
                    float mval = x.getValue();
                    newvalues.add(new PieEntry(cal + mval, val));
                    checks = false;
                    break;
                }else{
                    checks =true;
                    newvalues.add(new PieEntry(x.getValue(),x.getLabel()));
                }
            }
            if(checks){newvalues.add(new PieEntry(cal,val));}
            values = newvalues;
            dataSet = new PieDataSet(values,"Meals Calories");
        }else{
            values.add(new PieEntry(cal,val));
            dataSet = new PieDataSet(values,"Meals Calories");
        }

        dataSet.setSelectionShift(5f);
        dataSet.setSliceSpace(3f);

        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.WHITE);

        mchart.setData(data);
        mchart.invalidate();
    }*/

    public void setData(double count []){
        double recogCal = 2500 ;
        double recogCarb = 1250;
        double recogFats = 450;
        double recogPro = 750;

        ArrayList<BarEntry> yValues = new ArrayList<>();

        //values of the stacked array
        for(int i = 0; i<count.length; i++){
            if(i == 0) {
                //consumed cals from macro
                float val1 = ( float)count[i];
                // recommended cals from macro
                float val2 = ( float)(recogPro - (4*count[i]));
                if(val2 < 0) val2 = 0;
                //float val3 = (float) ;

                yValues.add(new BarEntry(i, new float[]{val1, val2}));
            }else if(i == 1) {
                //consumed cals from macro
                float val1 = ( float)count[i];
                // recommended cals from macro
                float val2 = ( float)(recogCarb - (4*count[i]));
                if(val2 < 0) val2 = 0;
                //float val3 = (float) ;

                yValues.add(new BarEntry(i, new float[]{val1, val2}));
            }else if(i == 2) {
                //consumed cals from macro
                float val1 = ( float)count[i];
                // recommended cals from macro
                float val2 = ( float)(recogFats - (9*count[i]));
                if(val2 < 0) val2 = 0;
                //float val3 = (float) ;

                yValues.add(new BarEntry(i, new float[]{val1, val2}));
            }else if(i == 3) {
                //consumed cals from macro
                float val1 = ( float)count[i];
                // recommended cals from macro
                float val2 = ( float)(recogCal - count[i]);
                if(val2 < 0) val2 = 0;
                //float val3 = (float) ;

                yValues.add(new BarEntry(i, new float[]{val1, val2}));
            }

        }
        BarDataSet set1;

        set1 = new BarDataSet(yValues, "Daily Micro Consumption");
        set1.setDrawIcons(false);
        set1.setStackLabels(new String[]{"Consumed","Remaining","Max Suggested"});

        BarData data = new BarData(set1);
        data.setValueFormatter(new PercentFormatter());

        mchart.setData(data);
        mchart.setFitBars(true);
        mchart.invalidate();

    }


    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.enableNavigation(mContext, navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        val = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    class LoadIntoDataBase extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            FoodEntry entry = new FoodEntry(foodname,nutrients[0],nutrients[1],nutrients[2],
                    ++AddFoodActivity.count, Utils.getCurrentDate());
            mDataBase.foodDao().insertFood(entry);
            return null;
        }

    }

    class LoadFromDataBase extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            entries = AppDatabase.getInstance(MainActivity.this).foodDao().loadAllFoods(Utils.getCurrentDate());
            return null;
        }

    }
    class LoadFromDataBase2 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            entries = AppDatabase.getInstance(MainActivity.this).foodDao().loadAllFoods(Utils.getYesterdayDateString());
            return null;
        }

    }

}
