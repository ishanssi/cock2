package com.ishan.Cocktailsapp.mainfragmentsfolder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ishan.Cocktailsapp.HorizontalNtbActivity;
import com.ishan.Cocktailsapp.R;
import com.ishan.Cocktailsapp.adapters.FavoriteAdapter;
import com.ishan.Cocktailsapp.roomdatabase.FavoriteList;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link favouritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class favouritesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View v;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView mainresView;
    private FavoriteAdapter adapter;

    public favouritesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment favouritesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static favouritesFragment newInstance(String param1, String param2) {
        favouritesFragment fragment = new favouritesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
          v= inflater.inflate(R.layout.fragment_favourites, container, false);
        mainresView = (RecyclerView)v. findViewById(R.id.resviewfav);

        // use a linear layout manager
        mainresView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        List<FavoriteList> favoriteLists= HorizontalNtbActivity.favoriteDatabase.favoriteDao().getFavoriteData();




        adapter=new FavoriteAdapter(favoriteLists,getContext());
        mainresView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        return  v;
    }

    @Override
    public void onResume() {
        super.onResume();

        mainresView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        List<FavoriteList> favoriteLists= HorizontalNtbActivity.favoriteDatabase.favoriteDao().getFavoriteData();



        adapter=new FavoriteAdapter(favoriteLists,getContext());
        mainresView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}