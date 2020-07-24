package com.umn.appdev.fitu;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.umn.appdev.fitu.Utils.Utils;
import com.umn.appdev.fitu.database.AppDatabase;
import com.umn.appdev.fitu.database.FoodEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FoodListActivity extends AppCompatActivity implements FoodAdapter.ListemItemClickListener{
    private static final String TAG = "FoodActivity";
    private static final int ACTIVITY_NUM = 2;
    private Context mContext = FoodListActivity.this;
    private Date date;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase mDataBase;
    private FoodEntry clickedCalendarEntry;
    private List<FoodEntry> mDataset;

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(this, AddFoodActivity.class);
        intent.putExtra("foodentry",mDataset.get(clickedItemIndex));
        startActivity(intent);
    }

    class LoadIntoDataBase extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            mDataset = mDataBase.foodDao().loadAllFoods(date);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mAdapter = new FoodAdapter(mDataset, FoodListActivity.this);
            recyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        Intent intent = getIntent();
        if (intent.hasExtra("date")) {
            String sdate = intent.getStringExtra("date");
            try {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(sdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else {

            date = Utils.getCurrentDate();
        }
        mDataBase = AppDatabase.getInstance(getApplicationContext());
        new LoadIntoDataBase().execute();
        recyclerView = (RecyclerView) findViewById(R.id.food_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //setupBottomNavigationView();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodListActivity.this, AddFoodActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new LoadIntoDataBase().execute();
    }
    /*
	private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.enableNavigation(mContext, navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
    */

}
