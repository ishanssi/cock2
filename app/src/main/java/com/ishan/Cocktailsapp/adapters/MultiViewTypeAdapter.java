package com.ishan.Cocktailsapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ishan.Cocktailsapp.R;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.util.List;

public class MultiViewTypeAdapter extends RecyclerView.Adapter<MultiViewTypeAdapter.ViewHolder>
        implements FastScrollRecyclerView.SectionedAdapter,
        FastScrollRecyclerView.MeasurableAdapter {


     private List<cocktailsmodel> peronlist;
     private Context appctx;
     private  ishanclicklistner<cocktailsmodel> personclick;


    public MultiViewTypeAdapter(List<cocktailsmodel> peronlist, Context appctx ) {
        this.peronlist = peronlist;
        this.appctx = appctx;






////////


    }

    public  void settingclicking( ishanclicklistner<cocktailsmodel> aa)
    {

        this.personclick=aa;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout, parent, false);
        return new MultiViewTypeAdapter.ViewHolder(view);
      //  return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
//        if (position % 4 == 0) {
//            return R.layout.list_item_header;
//        }
        return R.layout.cardlayout;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final cocktailsmodel personobj = peronlist.get(position);
        //holder.name.setText(personobj.getName());
        // holder.email.setText(personobj.getCategory());
        // holder.count.setText(""+(++position));
        holder.name.setText(getNameForItem(position));

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

    @Override
    public int getItemCount() {
        return peronlist.size();
    }

    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public String getSectionName(int position) {



        if (position % 4 == 0) {
            return String.format("H%d", (position / 4) + 1);
        } else {
            return String.format("%d", position - position / 4);
        }
    }

    @Override
    public int getViewTypeHeight(RecyclerView recyclerView,
                                 @Nullable RecyclerView.ViewHolder viewHolder, int viewType) {
        if (viewType == R.layout.list_item_header) {
            return recyclerView.getResources().getDimensionPixelSize(R.dimen.list_item_header_height);
        } else if (viewType == R.layout.cardlayout) {
            return recyclerView.getResources().getDimensionPixelSize(R.dimen.list_item_height);
        }
        return 0;
    }

    @SuppressLint("DefaultLocale")
    @NonNull
    private String getNameForItem(int position) {
        if (position % 4 == 0) {
            return String.format("Header %d", (position / 4) + 1);
        } else {
            return String.format("Item %d", position - position / 4);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView email;
        private TextView  count;
        private ImageView image;
        private CardView cardview;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView7);
            image = itemView.findViewById(R.id.imageView2);
            //  email = itemView.findViewById(R.id.textView8);
            // count = itemView.findViewById(R.id.txtcount);
            cardview = itemView.findViewById(R.id.mycardview);
        }
    }
}