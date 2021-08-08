package com.ishan.Cocktailsapp.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ishan.Cocktailsapp.R;
import com.ishan.Cocktailsapp.adapters.CardAdapterclass;
import com.ishan.Cocktailsapp.adapters.cocktailsmodel;
import com.ishan.Cocktailsapp.adapters.ishanclicklistner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class allingradientrecipies extends AppCompatActivity {
    RequestQueue queue;
    private cocktailsmodel popularmodelobj;
    public static   ArrayList<cocktailsmodel> popularlist;
    private RecyclerView mainresView;
    private CardAdapterclass mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allingradientrecipies);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        String ingname= (String) b.get("name");
        getSupportActionBar().setTitle(b.get("name").toString());


        queue = Volley.newRequestQueue(getApplicationContext());


        loadpopular(ingname);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return true;
    }
    private void loadpopular(String ingname) {

     //   https://www.thecocktaildb.com/api/json/v2/9973533/filter.php?i=vodka
        String popular="https://www.thecocktaildb.com/api/json/v2/9973533/filter.php?i="+ingname;
        //String popular="https://www.thecocktaildb.com/api/json/v2/9973533/popular.php";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, popular,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject json = null;
                        Log.d("Ishan", "response is " +response);
                        popularlist = new ArrayList<>();


                        try {
                            JSONObject responseJsonObject = new JSONObject(response);
                            JSONArray jsonArray = new JSONArray(responseJsonObject.getString("drinks"));

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jObj = jsonArray.getJSONObject(i);
                                String drinkID = jObj.getString("idDrink");
                                String drinkName = jObj.getString("strDrink");
                                // String category = jObj.getString("strCategory");
                                String imageurl = jObj.getString("strDrinkThumb");



                                popularmodelobj= new cocktailsmodel(drinkID,drinkName,imageurl);
                                popularlist.add(popularmodelobj);


                            }

                            mainresView = (RecyclerView) findViewById(R.id.resviewing);

                            // use a linear layout manager
                            mainresView.setLayoutManager(new GridLayoutManager(allingradientrecipies.this, 2));
                            mAdapter = new CardAdapterclass(popularlist, allingradientrecipies.this);

                            // set the adapter object to the Recyclerview
                            mainresView.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();

                            mAdapter.settingclicking(new ishanclicklistner<cocktailsmodel>() {


                                @Override
                                public void onItemClickperson(cocktailsmodel postion) {
                                    //  Toast.makeText(getContext(), postion.getDrinkName(), Toast.LENGTH_LONG).show();
                                    Intent ii=new Intent(allingradientrecipies.this, cocktailsDetailsPage.class);
                                    ii.putExtra("name", postion.getName());
                                    ii.putExtra("imageurl", postion.getImgurl());
                                    ii.putExtra("ID", postion.getID());


                                    Log.d("ishan", "ing and measure is aaaaaaaa + " +postion.getID());

                                    startActivity(ii);
                                    // startActivity(new Intent(getContext(), cocktailsDetailsPage.class));
                                }
                            });



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // textmain.setText(error.toString());
                Log.d("Ishan", "error is " + error);

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }
}