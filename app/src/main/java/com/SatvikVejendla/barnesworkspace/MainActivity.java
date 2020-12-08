package com.SatvikVejendla.barnesworkspace;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.SatvikVejendla.barnesworkspace.Fragments.APCalcAB;
import com.SatvikVejendla.barnesworkspace.Fragments.HonorsAl2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        SharedPreferences get = getSharedPreferences("user", MODE_APPEND);
        int cl = get.getInt("class", 0);
        int color = get.getInt("color", -740056);

        TabLayout tabLayout = findViewById(R.id.tablayout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        ViewPager vp = findViewById(R.id.viewpager);
        ViewPagerAdapter vpa = new ViewPagerAdapter(getSupportFragmentManager());
        vpa.addFragment(new HonorsAl2(), "Honors Algebra II");
        vpa.addFragment(new APCalcAB(), "AP Calculus AB");
        vp.setAdapter(vpa);
        tabLayout.setBackgroundColor(color);

        vp.setCurrentItem(cl);
        if(i.getBooleanExtra("back", true)){
            SharedPreferences get2 = getSharedPreferences("user", MODE_APPEND);
            int color2 = get2.getInt("color", -740056);
            int cl2 = get2.getInt("class", 0);
            tabLayout.setBackgroundColor(color2);
            fab.setBackgroundTintList(ColorStateList.valueOf(color));
            vp.setCurrentItem(cl2);

        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(i);
            }
        });

        tabLayout.setupWithViewPager(vp);

    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        ViewPagerAdapter(FragmentManager fm){
            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}