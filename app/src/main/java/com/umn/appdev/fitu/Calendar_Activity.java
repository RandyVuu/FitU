
package com.umn.appdev.fitu;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
public class Calendar_Activity extends AppCompatActivity
        implements CalendarAdapter.ListItemClickListener {

    private static final int NUM_LIST_ITEMS = 30;
    private CalendarAdapter mAdapter;
    private RecyclerView mNumbersList;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_);

        mNumbersList = (RecyclerView) findViewById(R.id.calendar_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mNumbersList.setLayoutManager(layoutManager);

        mNumbersList.setHasFixedSize(true);

        mAdapter = new CalendarAdapter(NUM_LIST_ITEMS, this);

        mNumbersList.setAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(int clickedItem) {
        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        startActivity(intentToStartDetailActivity);
    }
    //TODO have detail link to database
    //TODO create button that can change/add food plan to calendar day
    //TODO visual optimizations
}
