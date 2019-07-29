package com.umn.appdev.fitu;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.umn.appdev.fitu.database.AppDatabase;
import com.umn.appdev.fitu.database.FoodPlan;
import com.umn.appdev.fitu.database.FoodEntry;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FoodPlanActivity extends AppCompatActivity implements FoodPlanAdapter.ListemItemClickListener{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase mDataBase;
    private List<FoodPlan> mDataset;

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(this,AddFoodPlan.class);
        intent.putExtra("foodplan",mDataset.get(clickedItemIndex));
        startActivity(intent);
    }

    class LoadIntoDataBase extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            mDataset = mDataBase.planDao().getFoodPlan();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mAdapter = new FoodPlanAdapter(mDataset,FoodPlanActivity.this);
            recyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_plan);
        mDataBase = AppDatabase.getInstance(getApplicationContext());
        new LoadIntoDataBase().execute();
        recyclerView = (RecyclerView) findViewById(R.id.food_recycler_view2);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodPlanActivity.this, AddFoodPlan.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new LoadIntoDataBase().execute();
    }
}
