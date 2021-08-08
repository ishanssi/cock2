package com.ishan.Cocktailsapp.adapters;

import android.content.Context;
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
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CardAdapterclass2  extends RecyclerView.Adapter<CardAdapterclass2.myviewholders>    implements FastScrollRecyclerView.SectionedAdapter{

//// endless

    //////





    private List<cocktailsmodel> peronlist;
    private Context appctx;
    private  ishanclicklistner<cocktailsmodel> personclick;







    public CardAdapterclass2(List<cocktailsmodel> peronlist, Context appctx ) {
        this.peronlist = peronlist;
        this.appctx = appctx;






////////


    }

    @NonNull
    @NotNull
    @Override
    public CardAdapterclass2.myviewholders onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {



        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout, parent, false);
        return new CardAdapterclass2.myviewholders(view);



    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<cocktailsmodel> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        peronlist = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }



    @Override
    public void onBindViewHolder(@NonNull @NotNull CardAdapterclass2.myviewholders holder, int position) {
        final cocktailsmodel personobj = peronlist.get(position);

        holder.name.setText(personobj.getName());
        // holder.email.setText(personobj.getCategory());
        // holder.count.setText(""+(++position));


        String url=personobj.getImgurl();
        Glide.with(appctx).load(url).into(holder.image);




        // holder.cardview.setCardBackgroundColor(randomStr);
        //holder.layoutback.setBackgroundColor(randomStr);

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personclick.onItemClickperson(personobj);
            }
        });



    }


    public  void settingclicking( ishanclicklistner<cocktailsmodel> aa)
    {

        this.personclick=aa;
    }


    @Override
    public int getItemCount() {
        return peronlist.size();
    }

    @NonNull
    @NotNull
    @Override
    public String getSectionName(int position) {
        return String.format("%d", position + 1);
    }
    private String getNameForItem(int position) {
        return String.format("Item %d", position + 1);
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
