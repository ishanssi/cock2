package com.ishan.Cocktailsapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ishan.Cocktailsapp.R;
import com.ishan.Cocktailsapp.activities.cocktailsDetailsPage;
import com.ishan.Cocktailsapp.roomdatabase.FavoriteList;

import java.util.List;



public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<FavoriteList> favoriteLists;
    Context context;
    private  favclickinterface<FavoriteList> personclick;


    public FavoriteAdapter(List<FavoriteList> favoriteLists, Context context) {
        this.favoriteLists = favoriteLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardlayout,viewGroup,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {



        FavoriteList fl=favoriteLists.get(i);


        Glide.with(context).load(fl.getImage()).into(viewHolder.img);
       // Picasso.with(context).load(fl.getImage()).into(viewHolder.viewHolder);
        viewHolder.tv.setText(fl.getName());

        viewHolder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //personclick.onItemClickFAV(fl);



                Intent ii=new Intent(context, cocktailsDetailsPage.class);
                ii.putExtra("name", fl.getName());
                ii.putExtra("imageurl", fl.getImage());
                ii.putExtra("ID", fl.getId());


               // Log.d("ishan", "ing and measure is aaaaaaaa + " +fl.getId());

                context.startActivity(ii);




            }
        });


    }

    @Override
    public int getItemCount() {
        return favoriteLists.size();
    }

    public  void settingclicking( favclickinterface<FavoriteList> aa)
    {

        this.personclick=aa;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tv;
        private CardView cardview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.imageView2);
            tv=(TextView)itemView.findViewById(R.id.textView7);
            cardview =(CardView) itemView.findViewById(R.id.mycardview);
        }
    }
}