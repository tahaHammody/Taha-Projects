package com.example.finalproject;

import android.content.Context;
import android.hardware.camera2.params.Capability;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Logic.Room;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsHolder> {

    Context context;
    List<Room> rooms;

    public RoomsAdapter(Context context, List<Room> rooms) {
        this.context = context;
        this.rooms = rooms;
    }

    @NonNull
    @Override
    public RoomsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoomsHolder(LayoutInflater.from(context).inflate(R.layout.room_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomsHolder holder, int position) {
        holder.school.setText("School : " + String.valueOf(rooms.get(position).getSchool().getName()));
        holder.capacity.setText("Capacity : " + rooms.get(position).getCapacity());
        holder.floor.setText("Floor : " + rooms.get(position).getFloor());
        holder.num.setText("Num : " + rooms.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }
}
