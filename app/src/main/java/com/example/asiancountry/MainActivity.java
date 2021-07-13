package com.example.asiancountry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView countryList;
    RequestQueue requestQueue;
    List<itemData> countryDataList;
    public static String apiUrl = "https://restcountries.eu/rest/v2/region/asia";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryList = (RecyclerView) findViewById(R.id.countryList);
        countryList.setHasFixedSize(true);
        countryList.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        countryDataList = new ArrayList<>();
        fetchData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.room_db_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_RoomDb){
            Intent intent1 = new Intent(MainActivity.this,RoomDBActivity.class);
            startActivity(intent1);
        }
        return super.onOptionsItemSelected(item);
    }

    private void fetchData() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, apiUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String countryFlag = jsonObject.getString("flag");

                        String countryName = jsonObject.getString("name");
                        String countryCapital = jsonObject.getString("capital");
                        String countryRegion = jsonObject.getString("region");
                        String countrySubRegion = jsonObject.getString("subregion");
                        int countryPopulation = jsonObject.getInt("population");

                        itemData items = new itemData(countryName,countryCapital,countryFlag,countryRegion,countrySubRegion,countryPopulation);
                        countryDataList.add(items);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                        Toast.makeText(MainActivity.this, jsonException.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                    Adapter adapter = new Adapter(getApplicationContext(),countryDataList);

                    countryList.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }
}