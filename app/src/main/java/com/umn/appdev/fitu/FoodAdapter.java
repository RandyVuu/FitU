package com.umn.appdev.fitu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.umn.appdev.fitu.database.FoodEntry;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private List<FoodEntry> mDataset;
    public FoodAdapter(@NonNull List<FoodEntry> dataset) {
        mDataset = dataset;
    }
    @Override
    public FoodAdapter.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = new TextView(parent.getContext());
        return new FoodAdapter.FoodViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        holder.textView.setText(mDataset.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public FoodViewHolder(@NonNull TextView itemView) {
            super(itemView);
            textView = itemView;
        }
    }
}
