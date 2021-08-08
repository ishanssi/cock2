package com.ishan.Cocktailsapp.mainfragmentsfolder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ishan.Cocktailsapp.R;
import com.ishan.Cocktailsapp.activities.cocktailsDetailsPage;
import com.ishan.Cocktailsapp.activities.splashscreen;
import com.ishan.Cocktailsapp.adapters.CardAdapterclass;
import com.ishan.Cocktailsapp.adapters.cocktailsmodel;
import com.ishan.Cocktailsapp.adapters.ishanclicklistner;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentordinarydrinks#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentordinarydrinks extends Fragment {
    private CardAdapterclass mAdapter;
    private static ArrayList<cocktailsmodel> popularcocktaillist;
    String urlpopular="https://www.thecocktaildb.com/api/json/v2/9973533/popular.php";
    private cocktailsmodel popularmodelobj;
    private RecyclerView mainresView;
    private static final String EXTRA_COLOR = "color";
    private static final String EXTRA_url = "url";
    private int mColor;
    private String dataurl;
    RequestQueue queue;
     public View v;
    private static  int Extra_arraylisynumber;
    private ProgressDialog progressDialogload;
    private EditText serchview;

    public fragmentordinarydrinks() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static fragmentordinarydrinks newInstance( String url) {

        Bundle args = new Bundle();

        args.putString(EXTRA_url, url);

        fragmentordinarydrinks fragment = new fragmentordinarydrinks();
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





        v=inflater.inflate(R.layout.fragment_fragmentordinarydrinks, container, false);

        mainresView = (RecyclerView)v. findViewById(R.id.ordinaryresvie);


        // use a linear layout manager
        mainresView.setLayoutManager(new GridLayoutManager(getContext(), 2));
       // getdataCardadapter(dataurl);
        loadordinarydrinks();

        getActivity().setTitle("Ordinay Drinks");


        return v;
    }

    private void loadordinarydrinks() {
        // create an Object for Adapter
        mAdapter = new CardAdapterclass(splashscreen.ordinarylist, getContext());

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