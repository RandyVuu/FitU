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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.umn.appdev.fitu.Utils.BottomNavigationViewHelper;
import com.umn.appdev.fitu.Utils.Utils;
import com.umn.appdev.fitu.database.AppDatabase;
import com.umn.appdev.fitu.database.CalendarEntry;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;


import java.util.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static AppDatabase entry;
    private static final String TAG = "MainActivity";
    private static final int ACTIVITY_NUM = 0;

    private Context mContext = MainActivity.this;
    private AnyChartView mchart;

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
    List <CalendarEntry> entries;
    double[] nutrients = new double[3];

    private AppDatabase mDataBase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBottomNavigationView();

        mDataBase = AppDatabase.getInstance(getApplicationContext());

        final double[] dnutrients = new double[4];

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.meals, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        mchart =  findViewById(R.id.chart1);
        mchart.setBackgroundColor(Color.TRANSPARENT);
        //mchart.setMaxVisibleValueCount(40);

        foodcalname = (EditText) findViewById(R.id.foodid);
        numInPro = (EditText) findViewById(R.id.numpro);
        numInFat = (EditText) findViewById(R.id.numfat);
        numInCab = (EditText) findViewById(R.id.numcarbs);

        submitButton = (Button) findViewById(R.id.button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!foodcalname.getText().toString().isEmpty()) {
                    foodname = foodcalname.getText().toString();
                }
                if (!numInPro.getText().toString().isEmpty()) {
                    nutrients[0] = Integer.valueOf(numInPro.getText().toString());
                }
                if (!numInFat.getText().toString().isEmpty()) {
                    nutrients[2] = Integer.valueOf(numInFat.getText().toString());
                }
                if (!numInCab.getText().toString().isEmpty()) {
                    nutrients[1] = Integer.valueOf(numInCab.getText().toString());
                }
                /*if(cal == 0) {
                    cal = (4*carbs) + (9*fats) + (4*protein);
                }
                // generates graph by reading global cal and the current cal values in the graph already ?????
                //setData(10);

                //retreive list of the entries that is related to the current date

                //resets the global cal to 0
                cal =0;*/
                new LoadIntoDataBase().execute();

                new LoadFromDataBase().execute();

                if (entries == null || entries.size() == 0) {
                    new LoadFromDataBase2().execute();
                }
                //loop through lsit of entries and add the information for the current date into vals and update graph
//                if(entries != null) {
//                    for (CalendarEntry fde : entries) {
            }
        });

        if(entries == null||entries.size() == 0){
            new LoadFromDataBase2().execute();
        }
        //loop through lsit of entries and add the information for the current date into vals and update graph
        if(entries != null) {
            for (CalendarEntry fde : entries) {
                protein += fde.getProtein();
                carbs += fde.getCarbs();
                fats += fde.getFats();
                cal += fde.getCalories();

                dnutrients[0] = protein;
                dnutrients[1] = carbs;
                dnutrients[2] = fats;
                dnutrients[3] = cal;

                setData(dnutrients);
            }
        }
        //Load all food entry from current date into an array list

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

        Pie pie = AnyChart.pie();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Protien", (count[0]*4)));
        data.add(new ValueDataEntry("Carbs", (count[1]*4)));
        data.add(new ValueDataEntry("Fats", (4*9)));
        data.add(new ValueDataEntry("Total Calories", (count[3])));

        pie.data(data);

        pie.title("Daily Calories source");

        pie.labels().position("outside");

        //pie.legend().title().enabled(true);
        /*pie.legend().title()
                .text("")
                .padding(0d, 0d, 10d, 0d);*/

        pie.legend()
                .position("center-top")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        mchart.setChart(pie);

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
            CalendarEntry entry = new CalendarEntry(foodname,nutrients[0],nutrients[1],nutrients[2],
                    ++AddCFoodActivity.count, Utils.getCurrentDate());
            mDataBase.CalendarDao().insertFood(entry);
            return null;
        }

    }

    class LoadFromDataBase extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            entries = mDataBase.getInstance(MainActivity.this).CalendarDao().loadAllFoods(Utils.getCurrentDate());
            return null;
        }

    }
    class LoadFromDataBase2 extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            entries = mDataBase.getInstance(MainActivity.this).CalendarDao().loadAllFoods(Utils.getYesterdayDateString());
            return null;
        }

    }

}