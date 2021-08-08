package com.ishan.Cocktailsapp.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.ishan.Cocktailsapp.mainfragmentsfolder.SearchFragment;
import com.ishan.Cocktailsapp.mainfragmentsfolder.IngrdientFragment;
import com.ishan.Cocktailsapp.mainfragmentsfolder.favouritesFragment;

public class TabsMyAdapter extends FragmentStatePagerAdapter {
    private Context myContext;
    int totalTabs;
    public TabsMyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }



    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                IngrdientFragment homeFragment = new IngrdientFragment();
                return homeFragment;
            case 1:
                SearchFragment settingsFragment = new SearchFragment();
                return settingsFragment;
            case 2:
                favouritesFragment ff = new favouritesFragment();
                return ff;

            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}