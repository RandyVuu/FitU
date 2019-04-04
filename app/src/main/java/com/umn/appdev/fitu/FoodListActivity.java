package com.umn.appdev.fitu;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.umn.appdev.fitu.database.AppDatabase;
import com.umn.appdev.fitu.database.FoodEntry;
import com.umn.appdev.fitu.utils.Utils;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FoodListActivity extends AppCompatActivity implements FoodAdapter.ListemItemClickListener{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase mDataBase;
    private FoodEntry clickedFoodEntry;
    private List<FoodEntry> mDataset;

    @Override
    public void onListItemClick(int clickedItemIndex) {
         Intent intent = new Intent(this,AddFoodActivity.class);
         intent.putExtra("foodentry",mDataset.get(clickedItemIndex));
         startActivity(intent);
    }

    class LoadIntoDataBase extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            mDataset = mDataBase.foodDao().loadAllFoods(Utils.getCurrentDate());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mAdapter = new FoodAdapter(mDataset,FoodListActivity.this);
            recyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        mDataBase = AppDatabase.getInstance(getApplicationContext());
        new LoadIntoDataBase().execute();
        recyclerView = (RecyclerView) findViewById(R.id.food_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

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
}
