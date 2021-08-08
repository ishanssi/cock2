package com.ishan.Cocktailsapp.mainfragmentsfolder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.ishan.Cocktailsapp.R;
import com.ishan.Cocktailsapp.adapters.CardAdapterclass2;
import com.ishan.Cocktailsapp.adapters.cocktailsmodel;

import java.util.ArrayList;

import hari.bounceview.BounceView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link morefragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class morefragment extends Fragment {
    private CardAdapterclass2 mAdapter;
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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public morefragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        dataurl = getArguments().getString(EXTRA_url);
        //  mColor=android.R.color.transparent;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment morefragment.
     */
    // TODO: Rename and change types and number of parameters
    public static morefragment newInstance(String url) {
        Bundle args = new Bundle();

        args.putString(EXTRA_url, url);

        morefragment fragment = new morefragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       v= inflater.inflate(R.layout.fragment_morefragment, container, false);



        ImageView btnfeedbacks=(ImageView)v.findViewById(R.id.btnfeedbacks);
         btnfeedbacks.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {






               Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                       "mailto","ishanssi@gmail.com", null));
               emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feed backs for cocktails app");
               emailIntent.putExtra(Intent.EXTRA_TEXT, "Replace your feedback here !!");
               startActivity(Intent.createChooser(emailIntent, "Send email..."));
           }
       });


        ImageView btnrate=(ImageView)v.findViewById(R.id.btnateus);
        btnrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String appPackageName ="com.ishan.Cocktailsapp"; // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });

        ImageView btnmoreapps=(ImageView)v.findViewById(R.id.btnmoreapps);
        btnmoreapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String appPackageName ="com.slapps.sljobs";
                try {

                    https://play.google.com/store/apps/developer?id=Ishan+Swarnajith


                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Ishan+Swarnajith")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Ishan+Swarnajith")));
                }
            }
        });
        ImageView btnshare=(ImageView)v.findViewById(R.id.btnshare);
        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             String url="https://play.google.com/store/apps/details?id=com.ishan.Cocktailsapp";
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Look at this app found on playstore. "+url);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);


            }
        });


        ImageView btnabout=(ImageView)v.findViewById(R.id.btnaboutapp);
        btnabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                ViewGroup viewGroup = v.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.emailfeedback, viewGroup, false);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                BounceView.addAnimTo(alertDialog);
                alertDialog.show();
                alertDialog.setCancelable(true);

            }
        });
        ImageView btnprivacy=(ImageView)v.findViewById(R.id.btnprivacy);
        btnprivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                ViewGroup viewGroup = v.findViewById(android.R.id.content);

                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.privacyxml, viewGroup, false);


                WebView web = (WebView)dialogView.findViewById(R.id.webview1);
                web.loadUrl("file:///android_asset/privacy.html");






                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                BounceView.addAnimTo(alertDialog);
                alertDialog.show();
                alertDialog.setCancelable(true);

            }
        });






        return v;
    }


}