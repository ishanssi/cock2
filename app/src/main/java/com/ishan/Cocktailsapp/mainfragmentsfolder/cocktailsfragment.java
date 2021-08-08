package com.ishan.Cocktailsapp.mainfragmentsfolder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ishan.Cocktailsapp.R;
import com.ishan.Cocktailsapp.activities.cocktailsDetailsPage;
import com.ishan.Cocktailsapp.activities.splashscreen;
import com.ishan.Cocktailsapp.adapters.CardAdapterclass;
import com.ishan.Cocktailsapp.adapters.cocktailsmodel;
import com.ishan.Cocktailsapp.adapters.ishanclicklistner;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class cocktailsfragment extends Fragment {
    private CardAdapterclass mAdapter;
    private static   ArrayList<cocktailsmodel> cocktailslist;
    String urlpopular="https://www.thecocktaildb.com/api/json/v2/9973533/popular.php";
    private cocktailsmodel popularmodelobj;
    private RecyclerView mainresView;
    private static final String EXTRA_COLOR = "color";
    private static final String EXTRA_url = "url";
    private int mColor;
    private String dataurl;
    RequestQueue queue;
    View v;

    private ProgressDialog progressDialogload;



    public static cocktailsfragment newInstance( String url) {


        Bundle args = new Bundle();

        args.putString(EXTRA_url, url);

        cocktailsfragment fragment = new cocktailsfragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        dataurl = getArguments().getString(EXTRA_url);
      //  mColor=android.R.color.transparent;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        queue = Volley.newRequestQueue(getContext());




        v=inflater.inflate(R.layout.fragment_cocktailsfragment, container, false);
        mainresView = (RecyclerView)v. findViewById(R.id.cocktailsresview);


        // use a linear layout manager
        mainresView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        loadresview();
       // getdataCardadapter(dataurl);
//        if (cocktailslist==null)
//
//        {
//
//            getdataCardadapter(dataurl);
//            Toast.makeText(getContext(),"First time loading",Toast.LENGTH_LONG).show();
//        }
//        else
//        {
//            Toast.makeText(getContext(),"Second time loading",Toast.LENGTH_LONG).show();
//            updaterecycelview(cocktailslist);
//            // getdataCardadapter(dataurl);
//        }



        getActivity().setTitle("Cocktails");

        return v;
    }






    private void updaterecycelview(ArrayList<cocktailsmodel> cocktailslist) {

        mainresView = (RecyclerView)v. findViewById(R.id.popularresview);

        // use a linear layout manager
        mainresView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // create an Object for Adapter
        mAdapter = new CardAdapterclass(cocktailslist, getContext());

        // set the adapter object to the Recyclerview
        mainresView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        ///////


        /////



        mAdapter.settingclicking(new ishanclicklistner<cocktailsmodel>() {


            @Override
            public void onItemClickperson(cocktailsmodel postion) {
                //  Toast.makeText(getContext(), postion.getDrinkName(), Toast.LENGTH_LONG).show();
                Intent ii=new Intent(getContext(), cocktailsDetailsPage.class);
                ii.putExtra("title", postion.getName());
                ii.putExtra("imageurl", postion.getImgurl());
                ii.putExtra("ID", postion.getID());

//                                    ii.putExtra("ing1", postion.getIng1());
//                                    ii.putExtra("ing2", postion.getIng2());
//                                    ii.putExtra("ing3", postion.getIng3());
//                                    ii.putExtra("ing4", postion.getIng4());
//                                    ii.putExtra("ing5", postion.getIng5());
//
//                                    ii.putExtra("measure1", postion.getMeasure1());
//                                    ii.putExtra("measure2", postion.getMeasure2());
//                                    ii.putExtra("measure3", postion.getMeasure3());
//                                    ii.putExtra("measure4", postion.getMeasure4());
//                                    ii.putExtra("measure5", postion.getMeasure5());


                Log.d("ishan", "ing and measure is aaaaaaaa + " +postion.getID());

                startActivity(ii);
                // startActivity(new Intent(getContext(), cocktailsDetailsPage.class));
            }
        });



    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //  view.setBackgroundColor(ContextCompat.getColor(getContext(), mColor));
    }

    private  void loadresview()
    {
       // mainresView = (RecyclerView)v. findViewById(R.id.cocktailsresview);

        // use a linear layout manager
       // mainresView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mAdapter = new CardAdapterclass(splashscreen.cocktailslist, getContext());

        // set the adapter object to the Recyclerview
        mainresView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mAdapter.settingclicking(new ishanclicklistner<cocktailsmodel>() {


            @Override
            public void onItemClickperson(cocktailsmodel postion) {
                //  Toast.makeText(getContext(), postion.getDrinkName(), Toast.LENGTH_LONG).show();
                Intent ii=new Intent(getContext(), cocktailsDetailsPage.class);
                ii.putExtra("name", postion.getName());
                ii.putExtra("imageurl", postion.getImgurl());
                ii.putExtra("ID", postion.getID());



                Log.d("ishan", "ing and measure is aaaaaaaa + " +postion.getID());

                startActivity(ii);

            }
        });

    }

    private void getdataCardadapter(String newurl2) {
        progressDialogload = new ProgressDialog(getContext());
        progressDialogload.setMessage("Loading...");
        progressDialogload.setCancelable(false);
        //progressDialogload.show();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, newurl2,
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




                     //  progressDialogload.cancel();

                            mainresView = (RecyclerView)v. findViewById(R.id.cocktailsresview);

                            // use a linear layout manager
                            mainresView.setLayoutManager(new GridLayoutManager(getContext(), 2));

                            // create an Object for Adapter



                            mAdapter = new CardAdapterclass(cocktailslist, getContext());

                            // set the adapter object to the Recyclerview
                            mainresView.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();


                            mAdapter.settingclicking(new ishanclicklistner<cocktailsmodel>() {


                                @Override
                                public void onItemClickperson(cocktailsmodel postion) {
                                    //  Toast.makeText(getContext(), postion.getDrinkName(), Toast.LENGTH_LONG).show();
                                    Intent ii=new Intent(getContext(), cocktailsDetailsPage.class);
                                    ii.putExtra("title", postion.getName());
                                    ii.putExtra("imageurl", postion.getImgurl());
                                    ii.putExtra("ID", postion.getID());

//                                    ii.putExtra("ing1", postion.getIng1());
//                                    ii.putExtra("ing2", postion.getIng2());
//                                    ii.putExtra("ing3", postion.getIng3());
//                                    ii.putExtra("ing4", postion.getIng4());
//                                    ii.putExtra("ing5", postion.getIng5());
//
//                                    ii.putExtra("measure1", postion.getMeasure1());
//                                    ii.putExtra("measure2", postion.getMeasure2());
//                                    ii.putExtra("measure3", postion.getMeasure3());
//                                    ii.putExtra("measure4", postion.getMeasure4());
//                                    ii.putExtra("measure5", postion.getMeasure5());


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

    private void recycleviewaction() {
        mainresView = (RecyclerView)v. findViewById(R.id.popularresview);

        // use a linear layout manager
        mainresView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // create an Object for Adapter
        mAdapter = new CardAdapterclass(cocktailslist, getContext());

        // set the adapter object to the Recyclerview
        mainresView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


        mAdapter.settingclicking(new ishanclicklistner<cocktailsmodel>() {


            @Override
            public void onItemClickperson(cocktailsmodel postion) {
                //  Toast.makeText(getContext(), postion.getDrinkName(), Toast.LENGTH_LONG).show();
                Intent ii=new Intent(getContext(), cocktailsDetailsPage.class);
                ii.putExtra("title", postion.getName());
                ii.putExtra("imageurl", postion.getImgurl());
                ii.putExtra("ID", postion.getID());

//                                    ii.putExtra("ing1", postion.getIng1());
//                                    ii.putExtra("ing2", postion.getIng2());
//                                    ii.putExtra("ing3", postion.getIng3());
//                                    ii.putExtra("ing4", postion.getIng4());
//                                    ii.putExtra("ing5", postion.getIng5());
//
//                                    ii.putExtra("measure1", postion.getMeasure1());
//                                    ii.putExtra("measure2", postion.getMeasure2());
//                                    ii.putExtra("measure3", postion.getMeasure3());
//                                    ii.putExtra("measure4", postion.getMeasure4());
//                                    ii.putExtra("measure5", postion.getMeasure5());


                Log.d("ishan", "ing and measure is aaaaaaaa + " +postion.getID());

                startActivity(ii);
                // startActivity(new Intent(getContext(), cocktailsDetailsPage.class));
            }
        });

    }



}