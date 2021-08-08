package com.ishan.Cocktailsapp.mainfragmentsfolder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ishan.Cocktailsapp.R;
import com.ishan.Cocktailsapp.activities.cocktailsDetailsPage;
import com.ishan.Cocktailsapp.activities.splashscreen;
import com.ishan.Cocktailsapp.adapters.CardAdapterclass;
import com.ishan.Cocktailsapp.adapters.cocktailsmodel;
import com.ishan.Cocktailsapp.adapters.ingradientsmodel;
import com.ishan.Cocktailsapp.adapters.ishanclicklistner;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link popularfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class popularfragment extends Fragment {
    private ingradientsmodel ingradientsobj;
    public static ArrayList<ingradientsmodel> ingradientlist;
    private CardAdapterclass mAdapter;
    private static ArrayList<cocktailsmodel> cocktailslist;
    String urlpopular="https://www.thecocktaildb.com/api/json/v2/9973533/popular.php";
    private cocktailsmodel popularmodelobj;
    private RecyclerView mainresView;
    private static final String EXTRA_COLOR = "color";
    private static final String EXTRA_url = "url";
    private int mColor;
    private String dataurl;
    RequestQueue queue;
    View v;
    private static  int Extra_arraylisynumber;
    private ProgressDialog progressDialogload;

    public  static   ArrayList<cocktailsmodel> cocklist;
    public  static   ArrayList<cocktailsmodel> normallist;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    // creating variables for
    // our ui components.
    private RecyclerView courseRV;

    // variable for our adapter
    // class and array list
    private CardAdapterclass adapter;
    private ArrayList<cocktailsmodel> courseModalArrayList;
    public static   ArrayList<cocktailsmodel>  ordinarylist;

    public popularfragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static popularfragment newInstance(String url) {
        Bundle args = new Bundle();

        args.putString(EXTRA_url, url);

        popularfragment fragment = new popularfragment();
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




        v=inflater.inflate(R.layout.fragment_popularfragment, container, false);

       // androidx.appcompat.widget.Toolbar tt= (Toolbar) v.findViewById(R.id.toolbar1);
       // tt.s
        mainresView = (RecyclerView)v. findViewById(R.id.popularresview);

        // use a linear layout manager
       // mainresView.setLayoutManager(new GridLayoutManager(getContext(), 2));
       /// getdataCardadapter(dataurl);

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mainresView.setLayoutManager(layoutManager);
            loadpopular();


        getActivity().setTitle("Cocktails");

        return v;
    }

    private void loadpopular() {


        // create an Object for Adapter
        mAdapter = new CardAdapterclass(splashscreen.popularlist, getContext());

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



                //fire the zoom animation




                // startActivity(new Intent(getContext(), cocktailsDetailsPage.class));
            }
        });

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //  view.setBackgroundColor(ContextCompat.getColor(getContext(), mColor));
    }


}