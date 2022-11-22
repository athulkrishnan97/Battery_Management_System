package com.project.batterymanagementsystem.layouts;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.google.android.material.tabs.TabLayout;
import com.project.batterymanagementsystem.R;


/**
 * Tab Layout for Main Activity.
 */
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

    /**
     * Creates the tabs for the layout.
     */
    public void createTabs() {
        addTab(R.drawable.ic_home_white_24dp, R.string.title_fragment_home);
        addTab(R.drawable.ic_chart_areaspline_white_24dp, R.string.title_fragment_stats);
    }

    /**
     * Adds a new tab to the layout provided the icon and string description resources.
     *
     * @param iconId Icon Id resource
     * @param contentDescriptionId Content Description Id resource
     */
    private void addTab(@DrawableRes int iconId, @StringRes int contentDescriptionId) {
        addTab(newTab()
                .setIcon(iconId)
                .setContentDescription(contentDescriptionId));
    }
}
