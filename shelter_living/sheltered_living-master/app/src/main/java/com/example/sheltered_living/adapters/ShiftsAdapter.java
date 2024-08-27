package com.example.sheltered_living.adapters;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sheltered_living.R;
import com.example.sheltered_living.SessionManager;
import com.example.sheltered_living.callbacks.OnDeleteClickListener;
import java.util.List;

public class ShiftsAdapter extends RecyclerView.Adapter<ShiftsAdapter.ShiftViewHolder> {

    private List<Long> shifts;
    private OnDeleteClickListener deleteClickListener;


    public ShiftsAdapter(List<Long> shifts, OnDeleteClickListener listener) {
        this.shifts = shifts;
        this.deleteClickListener = listener;
    }

    public void setItems(List<Long> shifts) {
        this.shifts = shifts;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ShiftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShiftViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.shift_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShiftViewHolder holder, int position) {
        holder.date.setText(DateUtils.formatDateTime(holder.itemView.getContext(), shifts.get(position), 0));
        holder.deleteBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteClickListener.onDeleteClicked(holder.getAdapterPosition());
            }
        });
        if (SessionManager.isManagerSession()) {
            holder.deleteBT.setVisibility(View.VISIBLE);
        } else {
            holder.deleteBT.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return shifts.size();
    }

    static class ShiftViewHolder extends RecyclerView.ViewHolder{

        TextView date;
        Button deleteBT;


        public ShiftViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            deleteBT = itemView.findViewById(R.id.delete);
        }
    }
}
