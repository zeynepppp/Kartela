package com.example.user.kartelabo;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ArrayList<Renk> list= new ArrayList<>();
    RecyclerView recyclerView;
    ColorAdapter adapter;
    LinearLayoutManager layoutManager;
    ImageView imageView;
    HttpHandler httpHandler;
    private static String url= "https://raw.githubusercontent.com/zeynepppp/json-color-array/master/renkler.json";
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);

       // list=new ArrayList<>();


      //  new  getRecipe().execute();

//        list.add(new Renk("#d2596e"));
   /*     list.add(new Renk("#2da691"));
        list.add(new Renk("#816ed2"));
        list.add(new Renk("#7e912d"));
        list.add(new Renk("#171033"));
        list.add(new Renk("#cfd5c3"));*/

        recyclerView=findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

      /*  adapter=new ColorAdapter(MainActivity.this,list);
        recyclerView.setAdapter(adapter);*/
        new GetContacts().execute();




    }
    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response

            String jsonStr = sh.makeServicesCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray colors = jsonObj.getJSONArray("Renkler");

                    // looping through All Contacts
                    for (int i = 0; i < colors.length(); i++) {
                        JSONObject c = colors.getJSONObject(i);
                        String kod = c.getString("renkKodu");
                        list.add(new Renk(kod));


                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            adapter=new ColorAdapter(MainActivity.this,list);
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new ColorAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    // list.get(position);
                    Toast.makeText(MainActivity.this,list.get(position).getRenkKodu(),Toast.LENGTH_LONG).show();
                    imageView.setBackgroundColor(Color.parseColor(list.get(position).getRenkKodu()));
                    
                }
            });

        }
    }


}
