package com.umn.appdev.fitu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.umn.appdev.fitu.database.CalendarEntry;
import com.umn.appdev.fitu.database.FoodEntry;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>  {
    private List<FoodEntry> mDataset;
    private static int viewHoldeCount = 0;
    final private ListemItemClickListener mOnClickListener;
    public FoodAdapter(@NonNull List<FoodEntry> dataset, ListemItemClickListener listener) {
        mDataset = dataset;
        mOnClickListener = listener;
    }
    @Override
    public FoodAdapter.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_list_item,parent,false);
        viewHoldeCount++;
        FoodViewHolder viewHolder = new FoodViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        holder.textView.setText(mDataset.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface ListemItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
    class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView = (TextView) itemView.findViewById(R.id.food_item);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onListItemClick(getAdapterPosition());
        }
    }
}
