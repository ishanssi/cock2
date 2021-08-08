package com.ishan.Cocktailsapp.activities;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import com.agik.swipe_button.Controller.OnSwipeCompleteListener;
import com.agik.swipe_button.View.Swipe_Button_View;
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
import com.ishan.Cocktailsapp.HorizontalNtbActivity;
import com.ishan.Cocktailsapp.R;
import com.ishan.Cocktailsapp.database.DatabaseHelper;
import com.ishan.Cocktailsapp.database.FavoriteDatabase;
import com.ishan.Cocktailsapp.roomdatabase.FavoriteList;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.rahman.dialog.Activity.SmartDialog;
import com.rahman.dialog.ListenerCallBack.SmartDialogClickListener;
import com.rahman.dialog.Utilities.SmartDialogBuilder;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import hari.bounceview.BounceView;

public class cocktailsDetailsPage extends AppCompatActivity  implements View.OnClickListener {
    View clickview;
    private Toolbar mActionBarToolbar;
    private ProgressDialog progressDialogload;
    RequestQueue queue;
    String   Dringdescription;
    String maining;
    TextView ing1;
    TextView ing2;
    TextView ing3;
    TextView ing4;
    TextView ing5;
    ImageView img;
    TextView m1;
    TextView m2;
    TextView m3;
    TextView m4;
    TextView m5;
    DatabaseHelper     databaseHelper;
    FavoriteList favoriteList;
    int ID;
    String name;
    String imageurl;

    public static FavoriteDatabase favoriteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_populardetailspage);

        queue = Volley.newRequestQueue(this);
         Intent iin= getIntent();
         Bundle b = iin.getExtras();
           ID=  Integer.parseInt(String.valueOf(b.get("ID")));
          name= (String) b.get("name");
          imageurl= (String) b.get("imageurl");


          queue = Volley.newRequestQueue(this);
          img=(ImageView)findViewById(R.id.imgfav);

        
        if (HorizontalNtbActivity.favoriteDatabase.favoriteDao().isFavorite(ID)!=1)
        {

            // not in the favourite list
             img.setVisibility(View.INVISIBLE);

        }
        else {

            // aleady in the fav list

            img.setVisibility(View.VISIBLE);


            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {




                    new SmartDialogBuilder(cocktailsDetailsPage.this)
                            .setTitle("Remove from favourites .. ?")
                            .setSubTitle("")
                            .setCancalable(true)


                            .setNegativeButtonHide(false)

                            .setNegativeButton("cancel", new SmartDialogClickListener() {
                                @Override
                                public void onClick(SmartDialog smartDialog) {
                                    smartDialog.dismiss();

                                }
                            })
                            .setPositiveButton("ok", new SmartDialogClickListener() {
                                @Override
                                public void onClick(SmartDialog smartDialog) {
                                    smartDialog.dismiss();


                                    FavoriteList favoriteList=new FavoriteList();

                                    int id=ID;
                                    String image=imageurl;
                                    String name2=name;

                                    favoriteList.setId(id);
                                    favoriteList.setImage(image);
                                    favoriteList.setName(name2);


                                    HorizontalNtbActivity.favoriteDatabase.favoriteDao().delete(favoriteList);
                                    Toast.makeText(getApplicationContext(),"Removed from favourites !!!",Toast.LENGTH_LONG).show();
                                    img.setVisibility(View.INVISIBLE);


                                }
                            })

                            .build().show();




                }
            });

        }


        ing1=(TextView)findViewById(R.id.textView25);ing1.setOnClickListener(this);
        ing2=(TextView)findViewById(R.id.textView26);ing2.setOnClickListener(this);
        ing3=(TextView)findViewById(R.id.textView27);ing3.setOnClickListener(this);
        ing4=(TextView)findViewById(R.id.textView28);ing4.setOnClickListener(this);
        ing5=(TextView)findViewById(R.id.textView29);ing5.setOnClickListener(this);


        ing1.setVisibility(View.INVISIBLE);
        ing2.setVisibility(View.INVISIBLE);
        ing3.setVisibility(View.INVISIBLE);
        ing4.setVisibility(View.INVISIBLE);
        ing5.setVisibility(View.INVISIBLE);

        m1=(TextView)findViewById(R.id.textView19);
        m2=(TextView)findViewById(R.id.textView20);
        m3=(TextView)findViewById(R.id.textView21);
        m4=(TextView)findViewById(R.id.textView22);
        m5=(TextView)findViewById(R.id.textView23);








        // setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(b.get("name").toString());



        ImageView imghead= (ImageView) findViewById(R.id.profile_image);
        CollapsingToolbarLayout cc= (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        NestedScrollView sv = (NestedScrollView)findViewById(R.id.nestedScrollView2);
        sv.scrollTo(0, sv.getBottom());

       // populatedata(  b);
        loadingradients(ID);



        if(b!=null)
        {
          //  String j =(String) b.get("title");

            Glide.with(this).load(b.get("imageurl")).into(imghead);
        }



    }

    private void loadingradients(int id) {
        Log.d("ishan ","response is for ID ssss "+id);
        String newurl2="https://www.thecocktaildb.com/api/json/v2/9973533/lookup.php?i="+id;
        Log.d("ishan ","response is for ID rrrr "+newurl2);
        progressDialogload = new ProgressDialog(this);
        progressDialogload.setMessage("Loading...");
        progressDialogload.setCancelable(false);
        progressDialogload.show();





        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, newurl2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialogload.cancel();
                        JSONObject json = null;
                        Log.d("Ishan", "response is " +response);
                     //   popularcocktaillist = new ArrayList<>();
                        Log.d("ishan ","response is for ID "+response);


                        try {
                            JSONObject responseJsonObject = new JSONObject(response);
                            JSONArray jsonArray = new JSONArray(responseJsonObject.getString("drinks"));
                           // for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jObj = jsonArray.getJSONObject(0);
                                String instruction = jObj.getString("strInstructions");
                                String ing1 = jObj.getString("strIngredient1");
                                if (ing1 == "null") ing1 = "";
                                String ing2 = jObj.getString("strIngredient2");
                                if (ing2 == "null") ing2 = "";
                                String ing3 = jObj.getString("strIngredient3");
                                if (ing3 == "null") ing3 = "";
                                String ing4 = jObj.getString("strIngredient4");
                                if (ing4 == "null") ing4= "";
                                String ing5 = jObj.getString("strIngredient5");
                                if (ing5 == "null") ing5 = "";

                                String m1 = jObj.getString("strMeasure1");
                                if (m1 == "null") m1 = "";
                                String m2 = jObj.getString("strMeasure2");
                                if (m2 == "null") m2 = "";
                                String m3 = jObj.getString("strMeasure3");
                                if (m3 == "null") m3 = "";
                                String m4 = jObj.getString("strMeasure4");
                                if (m4 == "null") m4 = "";
                                String m5 = jObj.getString("strMeasure5");
                                if (m5 == "null") m5 = "";




                                Log.d("ishan ","how to make is  "+instruction);
                           // }

                            populatedata(instruction,ing1,ing2,ing3,ing4,ing5,m1,m2,m3,m4,m5);



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




    private void populatedata(String instructoins,String in1,String in2,String in3,String in4 ,String in5,String ms1,String ms2,String ms3,String ms4,String ms5) {

        Swipe_Button_View   btn=(Swipe_Button_View)findViewById(R.id.start2);


        btn.setOnSwipeCompleteListener_forward_reverse(new OnSwipeCompleteListener() {
            @Override
            public void onSwipe_Forward(Swipe_Button_View swipeView) {



                Addtofavouties();




            }
            @Override
            public void onSwipe_Reverse(Swipe_Button_View swipeView) {
                //inactive function
            }
        });




        TextView aa= (TextView)findViewById(R.id.txthowtomake);
        aa.setText(instructoins);





          m1.setVisibility(View.INVISIBLE);
             m2.setVisibility(View.INVISIBLE);
            m3.setVisibility(View.INVISIBLE);
             m4.setVisibility(View.INVISIBLE);
         m5.setVisibility(View.INVISIBLE);








          if (in1=="")
          {
              ing1.setVisibility(View.INVISIBLE);
          }
          else
          {
              ing1.setVisibility(View.VISIBLE);
              ing1.setText(in1);

          }
        if (in2=="")
        {
            ing2.setVisibility(View.INVISIBLE);
        }
        else
        {
            ing2.setVisibility(View.VISIBLE);
            ing2.setText(in2);

        }


        if (in3=="")
        {
            ing3.setVisibility(View.INVISIBLE);
        }
        else
        {
            ing3.setVisibility(View.VISIBLE);
            ing3.setText(in3);

        }

        if (in4=="")
        {
            ing4.setVisibility(View.INVISIBLE);
        }
        else
        {
            ing4.setVisibility(View.VISIBLE);
            ing4.setText(in4);

        }
        if (in5=="")
        {
            ing5.setVisibility(View.INVISIBLE);
        }
        else
        {
            ing5.setVisibility(View.VISIBLE);
            ing5.setText(in5);

        }



//        ing2.setText( in2);
//        ing3.setText(in3 );
//        ing4.setText(in4 );
//        ing5.setText(in5 );


        if (ms1=="")
        {
            m1.setVisibility(View.INVISIBLE);
        }
        else
        {
            m1.setVisibility(View.VISIBLE);
            m1.setText(ms1);

        }
        if (ms2=="")
        {
            m2.setVisibility(View.INVISIBLE);
        }
        else
        {
            m2.setVisibility(View.VISIBLE);
            m2.setText(ms2);

        }
        if (ms3=="")
        {
            m3.setVisibility(View.INVISIBLE);
        }
        else
        {
            m3.setVisibility(View.VISIBLE);
            m3.setText(ms3);

        }
        if (ms4=="")
        {
            m4.setVisibility(View.INVISIBLE);
        }
        else
        {
            m4.setVisibility(View.VISIBLE);
            m4.setText(ms4);

        }

        if (ms5=="")
        {
            m5.setVisibility(View.INVISIBLE);
        }
        else
        {
            m5.setVisibility(View.VISIBLE);
            m5.setText(ms5);

        }

//


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

    @Override
    public void onClick(View view) {

        clickview=view;

        progressDialogload = new ProgressDialog(this);
        progressDialogload.setMessage("Loading...");
        progressDialogload.setCancelable(false);
        progressDialogload.show();


        switch (view.getId()) {


            case R.id.textView25:



                maining=ing1.getText().toString();
                loaddialogbox(clickview,maining);




                break;
            case R.id.textView26:
                maining=ing2.getText().toString();
                loaddialogbox(view,maining);
              //  Toast.makeText(getApplicationContext(),ing2.getText().toString(),Toast.LENGTH_LONG).show();
                break;
            case R.id.textView27:
                maining=ing3.getText().toString();
                loaddialogbox(view,maining);
               // Toast.makeText(getApplicationContext(),ing3.getText().toString(),Toast.LENGTH_LONG).show();
                break;
            case R.id.textView28:
                maining=ing4.getText().toString();
                loaddialogbox(view,maining);
               // Toast.makeText(getApplicationContext(),ing4.getText().toString(),Toast.LENGTH_LONG).show();
                break;
            case R.id.textView29:
                maining=ing5.getText().toString();
                loaddialogbox(view,maining);
               // Toast.makeText(getApplicationContext(),ing5.getText().toString(),Toast.LENGTH_LONG).show();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }    }



    private  void getingradientdata(String newurl2) {
        Log.d("Ishan", "response is for dialog 1 " );

        StringRequest stringRequest = new StringRequest(Request.Method.GET, newurl2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        returndescription(response);




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

    private void returndescription( String response) {



        JSONObject json = null;
        Log.d("Ishan", "response is for dialog response " +response);

        try {
            JSONObject responseJsonObject = new JSONObject(response);
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



        /// load dialog box;


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(clickview.getContext()).inflate(R.layout.customview, viewGroup, false);
        TextView txtdescription=(TextView)dialogView.findViewById(R.id.textView);
        Button btnviewal=(Button) dialogView.findViewById(R.id.button2);
        ImageView imgview=(ImageView) dialogView.findViewById(R.id.imageView);




        if (Dringdescription=="null")
        {
            Dringdescription="No description available !!!";
        }
        String maintext=Dringdescription;
        txtdescription.setText(maintext);


        btnviewal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent ii=new Intent(getApplicationContext(), allingradientrecipies.class);
                ii.putExtra("name", maining);
                ii.putExtra("value", 1);





                startActivity(ii);

               cocktailsDetailsPage.this.finish();
            }
        });

        /// loadimageurl;



         String url="https://www.thecocktaildb.com/images/ingredients/"+maining+".png";
        Log.d("Ishan", "response is for dialog response asdasdasd " +url);
        Glide.with(this).load(url).into(imgview);


        ProgressBar pp=(ProgressBar) dialogView.findViewById(R.id.igprogressbar);

        /////////////

        Glide.with(this)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        pp.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        pp.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imgview);
        ///////

        progressDialogload.cancel();


        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        BounceView.addAnimTo(alertDialog);



        alertDialog.show();
        alertDialog.setCancelable(true);




    }

    private void loaddialogbox(View v ,String ingname) {

        //step1 create url and box
        String url="https://www.thecocktaildb.com/api/json/v2/9973533/search.php?i="+ingname;

        getingradientdata(url);




    }



    private void Addtofavouties() {

// Table tableobj= new table

        int id=ID;
        String image=imageurl;
        String name2=name;

        if (HorizontalNtbActivity.favoriteDatabase.favoriteDao().isFavorite(id)!=1)
        {
            favoriteList=new FavoriteList();
            favoriteList.setId(id);
            favoriteList.setImage(image);
            favoriteList.setName(name2);
            HorizontalNtbActivity.favoriteDatabase.favoriteDao().addData(favoriteList);


            // Snack Bar to show success message that record saved successfully

            Toast.makeText(getApplicationContext(),"Addded to favouries ",Toast.LENGTH_LONG).show();
             img.setVisibility(View.VISIBLE);


            favouriteclicked();

        }
        else {

            ObjectAnimator
                    .ofFloat(img, "translationX", 0, 50, -50, 50, -50,50, -50, 6, -6, 0)
                    .setDuration(500)
                    .start();

        }



    }

    private void startSpringAnimation(View view){
        ScaleAnimation fade_in =  new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        fade_in.setDuration(500);     // animation duration in milliseconds
        fade_in.setFillAfter(true);    // If fillAfter is true, the transformation that this animation performed will persist when it is finished.
        view.startAnimation(fade_in);





    }

    private void favouriteclicked() {

        if (HorizontalNtbActivity.favoriteDatabase.favoriteDao().isFavorite(ID)!=1)
        {


        }
        else
        {


            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {




                    new SmartDialogBuilder(cocktailsDetailsPage.this)
                            .setTitle("Remove from favourites .. ?")
                            .setSubTitle("")
                            .setCancalable(true)


                            .setNegativeButtonHide(false)

                            .setNegativeButton("cancel", new SmartDialogClickListener() {
                                @Override
                                public void onClick(SmartDialog smartDialog) {
                                    smartDialog.dismiss();

                                }
                            })
                            .setPositiveButton("ok", new SmartDialogClickListener() {
                                @Override
                                public void onClick(SmartDialog smartDialog) {



                                    FavoriteList favoriteList=new FavoriteList();

                                    int id=ID;
                                    String image=imageurl;
                                    String name2=name;

                                    favoriteList.setId(id);
                                    favoriteList.setImage(image);
                                    favoriteList.setName(name2);


                                    HorizontalNtbActivity.favoriteDatabase.favoriteDao().delete(favoriteList);
                                    Toast.makeText(getApplicationContext(),"Removed from favourites !!!",Toast.LENGTH_LONG).show();
                                    img.setVisibility(View.INVISIBLE);
                                    smartDialog.dismiss();

                                }
                            })

                            .build().show();




                }
            });
        }



    }


}


