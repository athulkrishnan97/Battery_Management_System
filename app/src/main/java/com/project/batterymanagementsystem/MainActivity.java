package com.project.batterymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.project.batterymanagementsystem.adapters.TabAdapter;
import com.project.batterymanagementsystem.layouts.MainTabLayout;

public class MainActivity extends AppCompatActivity{
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        MainTabLayout tabLayout = findViewById(R.id.tab_layout);
        getSupportActionBar().setTitle("Battery Management System");
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
                getSupportActionBar().setTitle(tab.getContentDescription());

                if (tab.getPosition() == TabAdapter.TAB_CHARTS) {
                    //EventBus.getDefault().post(new RefreshChartEvent());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //  nop
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //scroll the active fragment's contents to the top when user taps the current tab
            }
        });

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // If device has Android version < 6.0 don't request permissions
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return;

        /*
         * Ask for necessary permissions on run-time
         * Permissions with a protection level above Normal need to be requested
         */
        //setupPermission(Manifest.permission.READ_PHONE_STATE, Config.PERMISSION_READ_PHONE_STATE);
    }
}