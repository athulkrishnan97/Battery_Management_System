package com.project.batterymanagementsystem.layouts;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.google.android.material.tabs.TabLayout;
import com.project.batterymanagementsystem.R;


//Layout of the tab for the main activity 

public class MainTabLayout extends TabLayout {

    public MainTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MainTabLayout(Context context) {
        super(context);
    }

    public MainTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //Create tabs on the main layout
    
    public void createTabs() {
        addTab(R.drawable.ic_home_white_24dp, R.string.title_fragment_home);
        addTab(R.drawable.ic_chart_areaspline_white_24dp, R.string.title_fragment_stats);
    }

  
    private void addTab(@DrawableRes int iconId, @StringRes int contentDescriptionId) {
        addTab(newTab()
                .setIcon(iconId)
                .setContentDescription(contentDescriptionId));
    }
}
