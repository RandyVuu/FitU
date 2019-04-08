package com.umn.appdev.fitu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

import androidx.recyclerview.widget.RecyclerView;


public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.NumberViewHolder> {
    private static final String TAG = CalendarAdapter.class.getSimpleName();
    final private ListItemClickListener mOnClickListener;
    private int mNumberItems;
    private static int viewHolderCount;

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);

    }
    public CalendarAdapter(int numberOfItems, ListItemClickListener listener){
        mNumberItems= numberOfItems;
        mOnClickListener = listener;
        viewHolderCount =0;
    }
    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        Context context = viewGroup.getContext();
        int layoutIdforListItem= R.layout.calendar_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdforListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position){
        holder.setIsRecyclable(false);
        Log.d(TAG, "#" + position);
        holder.bind(position);

    }
    @Override
    public int getItemCount(){
        return mNumberItems;
    }


class NumberViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener {
    TextView listItemNumberView;
    TextView viewHolderIndex;
    Calendar calendar = Calendar.getInstance();
    String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

    public NumberViewHolder(View itemView){
        super(itemView);
        listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
        viewHolderIndex = (TextView) itemView.findViewById(R.id.tv_view_holder_instance);
        itemView.setOnClickListener(this);
    }

    void bind(int listIndex){
        if (listIndex != 0) {
            calendar.add(Calendar.DATE, listIndex);
            currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        }
        listItemNumberView.setText(String.valueOf(listIndex)+"| "+ currentDate);
    }
    @Override
    public void onClick(View v){
        int clickedpos = getAdapterPosition();
        mOnClickListener.onListItemClick(clickedpos);
    }

}
}