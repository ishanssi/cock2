package com.ishan.Cocktailsapp.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ishan.Cocktailsapp.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ingraeintAdapter   extends RecyclerView.Adapter<ingraeintAdapter.myviewholders>{

    private List<ingradientsmodel> ingradientsmodelList;
    private Context appctx;
    private  inginterface<ingradientsmodel> ingclick;
    private ProgressBar probar;

    public ingraeintAdapter(List<ingradientsmodel> inglist, Context appctx ) {
        this.ingradientsmodelList = inglist;
        this.appctx = appctx;






////////


    }

    @NonNull
    @NotNull
    @Override
    public ingraeintAdapter.myviewholders onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingcardlayout, parent, false);
        return new ingraeintAdapter.myviewholders(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ingraeintAdapter.myviewholders holder, int position) {
        final ingradientsmodel ingobj = ingradientsmodelList.get(position);
        holder.name.setText(ingobj.getIngname());

      String url="https://www.thecocktaildb.com/images/ingredients/"+ingobj.getIngname()+".png";
        Log.d("ishan", "ing and measure is url is   "+url);










        Glide.with(appctx).load(url)
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .placeholder(R.drawable.favpng_drawing)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                        Log.d("ishan", "Error loading "+url);
                       // probar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        return false;
                    }
                }).into(holder.image);
       // Glide.with(appctx).load(url).into(holder.image);



        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingclick.onItemClickperson(ingobj);
            }
        });
    }
    public  void settingclicking( inginterface<ingradientsmodel> aa)
    {

        this.ingclick=aa;
    }


    @Override
    public int getItemCount() {
        return ingradientsmodelList.size();
    }

    public class myviewholders extends RecyclerView.ViewHolder
    {


        private TextView name;
        private TextView email;
        private TextView  count;
        private ImageView image;
        private CardView cardview;


        public myviewholders(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.textView7);
            image = itemView.findViewById(R.id.imageView2);
            //  email = itemView.findViewById(R.id.textView8);
            // count = itemView.findViewById(R.id.txtcount);
            cardview = itemView.findViewById(R.id.mycardview);


        }
    }

}


