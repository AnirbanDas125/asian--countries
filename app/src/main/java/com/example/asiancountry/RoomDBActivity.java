package com.example.asiancountry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RoomDBActivity extends AppCompatActivity {

    EditText countryName,countryCapital,countryRegion,countrySubRegion,countryPopulation;
    Button buttonSave,buttonDelete;
    RecyclerView savedList;

    Intent intent;
    List<RoomData> roomDataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    RoomDBAdapter roomDBAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_dbactivity);
        intent = getIntent();

        countryName =  findViewById(R.id.editText_countryName);
        countryCapital =  findViewById(R.id.editText_countryCapital);
        countryRegion =  findViewById(R.id.editText_countryRegion);
        countrySubRegion =  findViewById(R.id.editText_countrySubRegion);
        countryPopulation =  findViewById(R.id.editText_countryPopulation);

        countryName.setText(intent.getStringExtra("t1"));
        countryCapital.setText(intent.getStringExtra("t2"));
        countryRegion.setText(intent.getStringExtra("t3"));
        countrySubRegion.setText(intent.getStringExtra("t4"));
        countryPopulation.setText(intent.getStringExtra("t5"));

        buttonSave = findViewById(R.id.buttonSave);
        buttonDelete = findViewById(R.id.buttonDelete);
        savedList = findViewById(R.id.savedList);

        database=RoomDB.getInstance(this);
        roomDataList=database.mainDao().getAll();

        linearLayoutManager = new LinearLayoutManager(this);
        savedList.setLayoutManager(linearLayoutManager);

        roomDBAdapter = new RoomDBAdapter(RoomDBActivity.this,roomDataList);
        savedList.setAdapter(roomDBAdapter);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = "Country Name: "+countryName.getText().toString().trim();
                String text2 = "Country Capital: "+countryCapital.getText().toString().trim();
                String text3 = "Country Region: "+countryRegion.getText().toString().trim();
                String text4 = "Country Subregion: "+countrySubRegion.getText().toString().trim();
                String text5 = "Country Population: "+countryPopulation.getText().toString().trim();
                String msg = text1+"\n"+text2+"\n"+text3+"\n"+text4+"\n"+text5;

                if(!msg.equals("")){
                    RoomData data = new RoomData();
                    data.setText(msg);
                    database.mainDao().insert(data);
                    countryName.setText("");
                    countryCapital.setText("");
                    countryRegion.setText("");
                    countrySubRegion.setText("");
                    countryPopulation.setText("");
                    Toast.makeText(RoomDBActivity.this, "Data Saved!", Toast.LENGTH_SHORT).show();

                    roomDataList.clear();
                    roomDataList.addAll(database.mainDao().getAll());
                    roomDBAdapter.notifyDataSetChanged();

                }else{
                    Toast.makeText(RoomDBActivity.this, "Empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.mainDao().deleteAll(roomDataList);
                roomDataList.clear();
                roomDataList.addAll(database.mainDao().getAll());
                roomDBAdapter.notifyDataSetChanged();
                Toast.makeText(RoomDBActivity.this, "Deleted All!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}