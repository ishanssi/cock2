package com.ishan.Cocktailsapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.androidanimations.library.Techniques;
import com.ishan.Cocktailsapp.HorizontalNtbActivity;
import com.ishan.Cocktailsapp.R;
import com.ishan.Cocktailsapp.adapters.Constants;
import com.ishan.Cocktailsapp.adapters.cocktailsmodel;
import com.ishan.Cocktailsapp.adapters.ingradientsmodel;
import com.rahman.dialog.Activity.SmartDialog;
import com.rahman.dialog.ListenerCallBack.SmartDialogClickListener;
import com.rahman.dialog.Utilities.SmartDialogBuilder;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.turkergoksu.lib.PercentageView;

public class splashscreen extends AwesomeSplash {
    RequestQueue queue;
    PercentageView pp;
    public static   ArrayList<cocktailsmodel> cocktailslist;
    public static   ArrayList<cocktailsmodel>  ordinarylist;
    public static   ArrayList<cocktailsmodel> popularlist;
    public static ArrayList<ingradientsmodel> ingradientlist;
    private cocktailsmodel popularmodelobj;
    ProgressDialog  progressDialogload;
    private ingradientsmodel ingradientsobj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //setContentView(R.layout.splashxml);

      //  startActivity(new Intent(splashscreen.this, HorizontalNtbActivity.class));

     

    }

    private class AsyncTaskExample extends AsyncTask<String, String, ArrayList>
    {


        @Override
        protected ArrayList doInBackground(String... strings) {





            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList arrayList) {
            super.onPostExecute(arrayList);
        }
    }


            @Override
    public void initSplash(ConfigSplash configSplash) {

        if( isOnline())
        {
            queue = Volley.newRequestQueue(getApplicationContext());
            loadpopular();

            //Customize Circular Reveal
            configSplash.setBackgroundColor(R.color.primary); //any color you want form colors.xml
            configSplash.setAnimCircularRevealDuration(800); //int ms
            configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
            configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

            //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

            //Customize Logo
            configSplash.setLogoSplash(R.drawable.background_emotion_error); //or any other drawable
            configSplash.setAnimLogoSplashDuration(1000); //int ms
            configSplash.setAnimLogoSplashTechnique(Techniques.Bounce); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


            //Customize Path
          configSplash.setPathSplash(Constants.newimage); //set path String

            configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
            configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
            configSplash.setAnimPathStrokeDrawingDuration(3000);
            configSplash.setPathSplashStrokeSize(15); //I advise value be <5
            configSplash.setPathSplashStrokeColor(R.color.white); //any color you want form colors.xml
            configSplash.setAnimPathFillingDuration(300);
            configSplash.setPathSplashFillColor(R.color.Black); //path object filling color


            //Customize Title
            configSplash.setTitleSplash("Cocktail Magazine...");
            configSplash.setTitleTextColor(R.color.Black);


            configSplash.setTitleTextSize(30f); //float value
            configSplash.setAnimTitleDuration(1000);
            configSplash.setAnimTitleTechnique(Techniques.BounceInUp);

        }
        else
        {
            new SmartDialogBuilder(splashscreen.this)
                    .setTitle("Please chceck your newwork connectoin !!")
                    .setSubTitle("")
                    .setCancalable(true)


                    .setNegativeButtonHide(true)

                    .setPositiveButton("ok", new SmartDialogClickListener() {
                        @Override
                        public void onClick(SmartDialog smartDialog) {
                            smartDialog.dismiss();
                            splashscreen.this.finish();

                        }
                    })

                    .build().show();


        }




    }

    @Override
    public void animationsFinished() {

        progressDialogload = new ProgressDialog(splashscreen.this);
        progressDialogload.setMessage("almost there !! ");
        progressDialogload.setCancelable(false);













    }

    private void loadcocktails() {


        String urlcocktails="https://www.thecocktaildb.com/api/json/v2/9973533/filter.php?c=Cocktail";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlcocktails,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject json = null;
                        Log.d("Ishan", "response is " +response);
                        cocktailslist = new ArrayList<>();


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
                                cocktailslist.add(popularmodelobj);


                            }


                            startActivity(new Intent(splashscreen.this, HorizontalNtbActivity.class));
                            splashscreen.this.finish();

                            //progressDialogload.cancel();

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





    private void loadordinary() {



        String ulrnormal="https://www.thecocktaildb.com/api/json/v2/9973533/filter.php?c=Ordinary_Drink";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ulrnormal,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject json = null;
                        Log.d("Ishan", "response is " +response);
                        ordinarylist = new ArrayList<>();


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
                                ordinarylist.add(popularmodelobj);


                            }

                            loadcocktails();



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
    private void loadpopular() {

        String popular="https://www.thecocktaildb.com/api/json/v2/9973533/popular.php";
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

                            loadingradient();




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
    private void loadingradient() {



        String url="https://www.thecocktaildb.com/api/json/v2/9973533/list.php?i=list";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        JSONObject json = null;
                        Log.d("Ishan", "response is " +response);
                        ingradientlist = new ArrayList<>();


                        try {
                            JSONObject responseJsonObject = new JSONObject(response);
                            JSONArray jsonArray = new JSONArray(responseJsonObject.getString("drinks"));

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jObj = jsonArray.getJSONObject(i);

                                String drinkName = jObj.getString("strIngredient1");

                                Log.d("ishan", "ing and measure is name is  + " +drinkName);





                                // popularmodelobj = new popularcocktailsmodel(drinkID,drinkName,category,imageurl,instruction,ing1,ing2,ing3,ing4,ing5,measure1,measure2,measure3,measure4,measure5);
                                ingradientsobj= new ingradientsmodel(drinkName);
                                ingradientlist.add(ingradientsobj);


                            }


                            loadordinary();


                        }
                        catch (JSONException e) {
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

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
