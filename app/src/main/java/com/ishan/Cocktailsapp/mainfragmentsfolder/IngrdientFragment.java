package com.ishan.Cocktailsapp.mainfragmentsfolder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ishan.Cocktailsapp.R;
import com.ishan.Cocktailsapp.activities.allingradientrecipies;
import com.ishan.Cocktailsapp.activities.splashscreen;
import com.ishan.Cocktailsapp.adapters.inginterface;
import com.ishan.Cocktailsapp.adapters.ingradientsmodel;
import com.ishan.Cocktailsapp.adapters.ingraeintAdapter;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hari.bounceview.BounceView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IngrdientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IngrdientFragment extends Fragment {
    View v;
    String ingname2;
    String   Dringdescription;
    View clickview;
    private static ArrayList<ingradientsmodel> cocktailslist;
    String url="https://www.thecocktaildb.com/api/json/v2/9973533/list.php?i=list";
    private static final String EXTRA_url = "url";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RequestQueue queue;
    private RecyclerView mainresView;
    private ingradientsmodel ingradientsobj;
    private ingraeintAdapter mAdapter;
    private ProgressDialog progressDialogload;

    public IngrdientFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static IngrdientFragment newInstance(String param2) {
        Bundle args = new Bundle();

        args.putString(EXTRA_url, param2);

        IngrdientFragment fragment = new IngrdientFragment();
        fragment.setArguments(args);
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
     v= inflater.inflate(R.layout.fragment_category, container, false);

        loadingradientdata();
        //loadingradients(v,url);
        return v;


    }


    private void loadingradientdata()
    {
        mainresView = (RecyclerView)v. findViewById(R.id.resview);


        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mainresView.setLayoutManager(layoutManager);


       // mainresView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        // create an Object for Adapter
        mAdapter = new ingraeintAdapter(splashscreen.ingradientlist, getContext());

        // set the adapter object to the Recyclerview
        mainresView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mAdapter.settingclicking(new inginterface<ingradientsmodel>() {


            @Override
            public void onItemClickperson(ingradientsmodel postion) {
                //  Toast.makeText(getContext(), postion.getDrinkName(), Toast.LENGTH_LONG).show();
//                                    Intent ii=new Intent(getContext(), cocktailsDetailsPage.class);
//                                    ii.putExtra("title", postion.getName());
//                                    ii.putExtra("imageurl", postion.getImgurl());
//                                    ii.putExtra("ID", postion.getID());


                progressDialogload = new ProgressDialog(getContext());
                progressDialogload.setMessage("Please wait...");
                progressDialogload.setCancelable(false);
                progressDialogload.show();

            loaddialogbox(postion.getIngname());
                
                
                Log.d("ishan", "ing and measure is aaaaaaaa + " +postion.getIngname());

//                                    startActivity(ii);
                // startActivity(new Intent(getContext(), cocktailsDetailsPage.class));
            }
        });
    }

    private void loaddialogbox(String ingname) {
        queue = Volley.newRequestQueue(getContext());
         ingname2=ingname;

        Log.d("Ishan", "ingradien name is is sisi is " + ingname2);
        String url="https://www.thecocktaildb.com/api/json/v2/9973533/search.php?i="+ingname;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



            loaddialogdata(response);



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // textmain.setText(error.toString());
                Log.d("Ishan", "error is " + error);

            }
        });

        queue.add(stringRequest);

    }

    private void loaddialogdata(String responce) {


        JSONObject json = null;

        try {
            JSONObject responseJsonObject = new JSONObject(responce);
            JSONArray jsonArray = new JSONArray(responseJsonObject.getString("ingredients"));
            JSONObject jObj = jsonArray.getJSONObject(0);
             Dringdescription = jObj.getString("strDescription");
            Dringdescription.replaceAll("[^A-Za-z0-9]","");
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject jObj = jsonArray.getJSONObject(i);
//
//                               String   Dringdescription = jObj.getString("strDescription");
//
//                            }
            Log.d("Ishan", "response is for dialog desccc " +Dringdescription);
            // loaddialogbox(clickview,Dringdescription);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.customview, null);
        builder.setView(dialogView);




        TextView txtdescription=(TextView)dialogView.findViewById(R.id.textView);
        Button btnviewal=(Button) dialogView.findViewById(R.id.button2);
        ImageView imgview=(ImageView) dialogView.findViewById(R.id.imageView);





        if (Dringdescription=="null")
        {
            Dringdescription="No description available !!!";
        }
        String maintext=Dringdescription;
        txtdescription.setText(maintext);
        progressDialogload.cancel();

        String url="https://www.thecocktaildb.com/images/ingredients/"+ingname2+".png";
//        Log.d("Ishan", "response is for dialog response asdasdasd " +url);
//        Glide.with(this).
//                load(url) .
//                into(imgview);




        ////


        ProgressBar pp2=(ProgressBar) dialogView.findViewById(R.id.igprogressbar);

        /////////////

        Glide.with(this)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        pp2.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        pp2.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imgview);

        /////






        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        BounceView.addAnimTo(alertDialog);



        alertDialog.show();
        alertDialog.setCancelable(true);
        btnviewal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent ii=new Intent(getContext(), allingradientrecipies.class);
                ii.putExtra("name", ingname2);
                ii.putExtra("value", 1);





                startActivity(ii);


            }
        });
    }


}