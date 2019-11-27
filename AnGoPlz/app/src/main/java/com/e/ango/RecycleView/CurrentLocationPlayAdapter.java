package com.e.ango.RecycleView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.ango.R;

import java.util.ArrayList;

public class CurrentLocationPlayAdapter extends RecyclerView.Adapter<CurrentLocationPlayAdapter.ViewHolder> {
    ArrayList<CurrentLocationPlay> items = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recycle_items, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CurrentLocationPlay item = items.get(position);
        holder.setItem(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        TextView textView4;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView4 = itemView.findViewById(R.id.textView3);
        }

        public void setItem(CurrentLocationPlay item) {
            textView.setText(item.getAddress());
            textView2.setText(item.getCategory_name());
            textView4.setText(item.getTitle());
        }


    }

    public void addItem(CurrentLocationPlay item) {
        items.add(item);
    }

    public void setItems(ArrayList<CurrentLocationPlay> items) {
        this.items = items;
    }

    public CurrentLocationPlay getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, CurrentLocationPlay item) {
        items.set(position, item);
    }

}
