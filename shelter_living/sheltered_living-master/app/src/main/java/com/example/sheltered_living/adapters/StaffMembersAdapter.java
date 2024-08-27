package com.example.sheltered_living.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sheltered_living.R;
import com.example.sheltered_living.callbacks.OnAddShiftClickListener;
import com.example.sheltered_living.callbacks.OnAddTaskClickListener;
import com.example.sheltered_living.models.StaffMember;

import java.util.ArrayList;

public class StaffMembersAdapter extends RecyclerView.Adapter<StaffMembersAdapter.StaffMemberVH> {

    private ArrayList<StaffMember> items = new ArrayList<>();
    private OnAddTaskClickListener onAddTaskClickListener;
    private OnAddShiftClickListener onAddShiftClickListener;
    public void setItems(ArrayList<StaffMember> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public StaffMembersAdapter(OnAddShiftClickListener onAddShiftClickListener, OnAddTaskClickListener onAddTaskClickListener) {
        this.onAddShiftClickListener = onAddShiftClickListener;
        this.onAddTaskClickListener = onAddTaskClickListener;
    }

    @NonNull
    @Override
    public StaffMemberVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StaffMemberVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.staff_member_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StaffMemberVH holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(items.get(position).getImageUrl())
                .circleCrop()
                .placeholder(R.drawable.baseline_person_24)
                .into(holder.image);

        holder.name.setText(items.get(position).getName());
        holder.phone.setText(items.get(position).getPhone());
        holder.email.setText(items.get(position).getEmail());

        holder.addTaskBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddTaskClickListener.onAddTaskClicked(holder.getAdapterPosition(), items.get(holder.getAdapterPosition()));
            }
        });

        holder.scheduleBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddShiftClickListener.onAddShiftClicked(position, items.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class StaffMemberVH extends RecyclerView.ViewHolder {

        TextView name;
        TextView email;
        TextView phone;

        ImageView image;
        Button addTaskBT;
        Button scheduleBT;

        public StaffMemberVH(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            email = view.findViewById(R.id.email);
            phone = view.findViewById(R.id.phone);
            addTaskBT = view.findViewById(R.id.tasksBT);
            scheduleBT = view.findViewById(R.id.scheduleBT);
            image = view.findViewById(R.id.image);

        }

    }
}
