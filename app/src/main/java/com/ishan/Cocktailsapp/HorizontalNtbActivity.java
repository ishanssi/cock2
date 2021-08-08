package com.ishan.Cocktailsapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.ishan.Cocktailsapp.adapters.ViewPagerAdapter;
import com.ishan.Cocktailsapp.adapters.tabsfragment;
import com.ishan.Cocktailsapp.database.FavoriteDatabase;
import com.ishan.Cocktailsapp.mainfragmentsfolder.cocktailsfragment;
import com.ishan.Cocktailsapp.mainfragmentsfolder.fragmentordinarydrinks;
import com.ishan.Cocktailsapp.mainfragmentsfolder.morefragment;
import com.ishan.Cocktailsapp.mainfragmentsfolder.popularfragment;

import devlight.io.library.ntb.NavigationTabBar;

import java.util.ArrayList;

/**
 * Created by GIGAMOLE on 28.03.2016.
 */
public class HorizontalNtbActivity extends AppCompatActivity {
    private ViewPagerAdapter viewPagerAdapter;
    public static FavoriteDatabase favoriteDatabase;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_ntb);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        favoriteDatabase= Room.databaseBuilder(getApplicationContext(), FavoriteDatabase.class,"myfavdb").allowMainThreadQueries().build();
        initUI();
    }

    private void initUI() {
        String urlcocktails="https://www.thecocktaildb.com/api/json/v2/9973533/filter.php?c=Cocktail";
        String ulrnormal="https://www.thecocktaildb.com/api/json/v2/9973533/filter.php?c=Ordinary_Drink";
        String popular="https://www.thecocktaildb.com/api/json/v2/9973533/popular.php";
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.add( cocktailsfragment.newInstance(urlcocktails), "Page 1");
        viewPagerAdapter.add( fragmentordinarydrinks.newInstance(ulrnormal), "Page 2");
        viewPagerAdapter.add( popularfragment.newInstance(popular), "Page 3");
        viewPagerAdapter.add( tabsfragment.newInstance(urlcocktails), "Page 1");
        viewPagerAdapter.add( morefragment.newInstance(ulrnormal), "Page 2");
        viewPager.setAdapter(viewPagerAdapter);


        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);

        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.cock2icon)
                        , Color.parseColor(colors[2]))
                        .selectedIcon(getResources().getDrawable(R.drawable.cock2icon))
                        .title("Cocktails")
                        .badgeTitle("NTB")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ordinaryicon)
                        , Color.parseColor(colors[2]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ordinaryicon))
                        .title("Ordinary")
                        .badgeTitle("with")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.popularicon)
                        , Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.popularicon))
                        .title("Popular")
                        .badgeTitle("Latest")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.mroeicon)
                        ,Color.parseColor(colors[2]))
                        .selectedIcon(getResources().getDrawable(R.drawable.mroeicon))
                        .title("More")
                        .badgeTitle("icon")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.info)
                        ,Color.parseColor(colors[2]))
                        .selectedIcon(getResources().getDrawable(R.drawable.info))
                        .title("Info")
                        .badgeTitle("777")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 2);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           // model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }
}
