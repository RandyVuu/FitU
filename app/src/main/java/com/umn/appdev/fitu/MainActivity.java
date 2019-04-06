package com.umn.appdev.fitu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.umn.appdev.fitu.utils.BottomNavigationViewHelper;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "MainActivity";
    private static final int ACTIVITY_NUM = 0;

    private Context mContext = MainActivity.this;
    private PieChart mchart;
    private int cal =0;
    private int protein =0;
    private int fats =0;
    private int carbs =0;
    private String val;
    EditText numInCal;
    EditText numInPro;
    EditText numInFat;
    EditText numInCab;
    Button submitButton;
    ArrayList<PieEntry> values = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBottomNavigationView();

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.meals, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        mchart = (PieChart) findViewById(R.id.chart1);
        mchart.setBackgroundColor(Color.TRANSPARENT);

        numInCal = (EditText) findViewById(R.id.numcal);
        numInPro = (EditText) findViewById(R.id.numpro);
        numInFat = (EditText) findViewById(R.id.numfat);
        numInCab = (EditText) findViewById(R.id.numcarbs);

        submitButton = (Button) findViewById(R.id.button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!numInCal.getText().toString().isEmpty()){cal = Integer.valueOf(numInCal.getText().toString());}
                if(!numInPro.getText().toString().isEmpty()){protein = Integer.valueOf(numInPro.getText().toString());}
                if(!numInFat.getText().toString().isEmpty()){fats = Integer.valueOf(numInFat.getText().toString());}
                if(!numInCab.getText().toString().isEmpty()){carbs = Integer.valueOf(numInCab.getText().toString());}
                if(cal == 0) {
                    cal = (4*carbs) + (9*fats) + (4*protein);
                }
                setData();
                cal =0;
            }
        });



    }

    private void setData(){
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

}
