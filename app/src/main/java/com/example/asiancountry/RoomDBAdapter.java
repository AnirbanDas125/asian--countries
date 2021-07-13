package com.example.asiancountry;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomDBAdapter extends RecyclerView.Adapter<RoomDBAdapter.RoomDBHolder> {

    private List<RoomData> roomDataList;
    private Activity context;
    private RoomDB database;

    public RoomDBAdapter(Activity context,List<RoomData> roomDataList) {
        this.roomDataList = roomDataList;
        this.context = context;
       notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RoomDBHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room_item,parent,false);
        return new RoomDBHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  RoomDBAdapter.RoomDBHolder holder, int position) {
          RoomData data = roomDataList.get(position);

          holder.RoomText.setText(data.getText());
    }

    @Override
    public int getItemCount() {
        return roomDataList.size();
    }

    public class RoomDBHolder extends RecyclerView.ViewHolder{

        TextView RoomText;
        public RoomDBHolder(@NonNull  View itemView) {
            super(itemView);
            RoomText = itemView.findViewById(R.id.RoomText);
        }
    }
}
