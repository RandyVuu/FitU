package com.umn.appdev.fitu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.umn.appdev.fitu.database.FoodEntry;
import com.umn.appdev.fitu.database.FoodPlan;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodPlanAdapter extends RecyclerView.Adapter<FoodPlanAdapter.FoodViewHolder>  {
    private List<FoodPlan> mDataset;
    private static int viewHolderCount = 0;
    final private ListemItemClickListener mOnClickListener;
    public FoodPlanAdapter(@NonNull List<FoodPlan> dataset, ListemItemClickListener listener) {
        mDataset = dataset;
        mOnClickListener = listener;
    }
    @Override
    public FoodPlanAdapter.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_list_item,parent,false);
        viewHolderCount++;
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
