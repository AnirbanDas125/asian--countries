package com.example.asiancountry;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {

    private Context context;
    private List<itemData> countryDataList;

    public Adapter(Context context, List<itemData> countryDataList) {
        this.context = context;
        this.countryDataList = countryDataList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item , parent , false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.viewHolder holder, int position) {
        itemData itemdata = countryDataList.get(position);

        Glide.with(context).load(itemdata.getCountryFlag()).into(holder.countryFlag);
        holder.countryName.setText(itemdata.getCountryName());
        holder.countryCapital.setText(itemdata.getCountryCapital());
        holder.countryRegion.setText(itemdata.getCountryRegion());
        holder.countrySubRegion.setText(itemdata.getCountrySubRegion());
        holder.countryPopulation.setText(Integer.toString(itemdata.getCountryPopulation()));
       // Picasso.get().load(countryDataList.get(position).getCountryFlag()).into(holder.countryFlag);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String t1= holder.countryName.getText().toString().trim();
              String t2 = holder.countryCapital.getText().toString().trim();
              String t3= holder.countryRegion.getText().toString().trim();
              String t4 = holder.countrySubRegion.getText().toString().trim();
              String t5 = holder.countryPopulation.getText().toString().trim();

              Intent intent = new Intent(context,RoomDBActivity.class);
              intent.putExtra("t1",t1);
              intent.putExtra("t2",t2);
              intent.putExtra("t3",t3);
              intent.putExtra("t4",t4);
              intent.putExtra("t5",t5);

              context.startActivity(intent);
                Toast.makeText(context, "You Selected "+itemdata.getCountryName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return countryDataList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

         ImageView countryFlag;
        TextView countryName,countryCapital,countryRegion,countryPopulation,countrySubRegion;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            countryFlag = itemView.findViewById(R.id.countryFlag);
            countryName = itemView.findViewById(R.id.countryName);
            countryCapital = itemView.findViewById(R.id.countryCapital);
            countryRegion = itemView.findViewById(R.id.countryRegion);
            countrySubRegion = itemView.findViewById(R.id.countrySubregion);
            countryPopulation = itemView.findViewById(R.id.countryPopulation);

        }
    }
}
