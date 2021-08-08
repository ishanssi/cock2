package com.ishan.Cocktailsapp.mainfragmentsfolder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ishan.Cocktailsapp.R;
import com.ishan.Cocktailsapp.activities.cocktailsDetailsPage;
import com.ishan.Cocktailsapp.adapters.CardAdapterclass;
import com.ishan.Cocktailsapp.adapters.cocktailsmodel;
import com.ishan.Cocktailsapp.adapters.ishanclicklistner;
import com.meet.quicktoast.Quicktoast;
import com.rahman.dialog.Activity.SmartDialog;
import com.rahman.dialog.ListenerCallBack.SmartDialogClickListener;
import com.rahman.dialog.Utilities.SmartDialogBuilder;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    View v;
    private static ArrayList<cocktailsmodel> cocktailslist;
    String url="www.thecocktaildb.com/api/json/v2/1/search.php?s=margarita";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView searchresview;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RequestQueue queue;
    private ProgressDialog progressDialogload;
    private cocktailsmodel popularmodelobj;
    SearchView schview;
    private CardAdapterclass mAdapter;
    private LinearLayout ll;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {


        SearchFragment fragment = new SearchFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          v= inflater.inflate(R.layout.fragment_allcategories, container, false);
        queue = Volley.newRequestQueue(getContext());
        searchresview = (RecyclerView)v. findViewById(R.id.resview);

        // use a linear layout manager
        searchresview.setLayoutManager(new GridLayoutManager(getContext(), 2));

          schview=(SearchView)v.findViewById(R.id.searchView);
            ll=(LinearLayout)v.findViewById(R.id.linerlayout);

            ll.setVisibility(View.VISIBLE);




        schview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //Toast.makeText(getActivity(),schview.getQuery(),Toast.LENGTH_LONG).show();

                loadsearchdata(schview.getQuery().toString(),v);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        return v;
    }

    private void loadsearchdata(String text,View v) {
        searchresview.setVisibility(View.VISIBLE);
        String urlsearch="https://www.thecocktaildb.com/api/json/v2/9973533/search.php?s="+text;
        progressDialogload = new ProgressDialog(getContext());
        progressDialogload.setMessage("Please wait...");
        progressDialogload.setCancelable(false);
        progressDialogload.show();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlsearch,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                progressDialogload.cancel();
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




                                // popularmodelobj = new popularcocktailsmodel(drinkID,drinkName,category,imageurl,instruction,ing1,ing2,ing3,ing4,ing5,measure1,measure2,measure3,measure4,measure5);
                                popularmodelobj= new cocktailsmodel(drinkID,drinkName,imageurl);
                                cocktailslist.add(popularmodelobj);


                            }

                              Quicktoast toast = new Quicktoast(getContext());
                              toast.linfo(cocktailslist.size()+" results Found !");




                            // create an Object for Adapter
                            mAdapter = new CardAdapterclass(cocktailslist, getContext());

                            // set the adapter object to the Recyclerview
                            searchresview.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();

                            schview.setQuery("", false);
                            schview.clearFocus();
                            ll.setVisibility(View.INVISIBLE);

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
                                    // startActivity(new Intent(getContext(), cocktailsDetailsPage.class));
                                }
                            });


                        } catch (JSONException e) {


                            updateresview();
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

    private void updateresview() {
//
//cocktailslist.clear();
//mAdapter.notifyDataSetChanged();
     searchresview.setVisibility(View.INVISIBLE);
        ll.setVisibility(View.VISIBLE);
     cocktailslist.clear();
        new SmartDialogBuilder(getContext())
                .setTitle("No results found")
                .setSubTitle("")
                .setCancalable(true)


                .setNegativeButtonHide(true)

                .setPositiveButton("ok", new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {
                        smartDialog.dismiss();

                        schview.setQuery("", false);
                        schview.clearFocus();

                    }
                })

                .build().show();

    }


}

