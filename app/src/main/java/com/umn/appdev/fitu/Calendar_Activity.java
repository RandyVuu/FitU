
package com.umn.appdev.fitu;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.umn.appdev.fitu.utils.BottomNavigationViewHelper;

public class Calendar_Activity extends AppCompatActivity implements CalendarAdapter.ListItemClickListener {

	private static final String TAG = "CalendarActivity";
    private static final int ACTIVITY_NUM = 1;

    private static final int NUM_LIST_ITEMS = 30;
    private CalendarAdapter mAdapter;
    private RecyclerView mNumbersList;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_);
		setupBottomNavigationView();

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

	private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.enableNavigation(context, navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
