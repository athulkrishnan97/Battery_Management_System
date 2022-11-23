package com.project.batterymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.project.batterymanagementsystem.adapters.TabAdapter;
import com.project.batterymanagementsystem.layouts.MainTabLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity{
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Battery Management System");
        loadViews();
    }

    private void loadViews() {
        mViewPager = findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(TabAdapter.NUM_TABS - 1);

        final TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(tabAdapter);

        MainTabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.createTabs();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                Objects.requireNonNull(getSupportActionBar()).setTitle(tab.getContentDescription());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}