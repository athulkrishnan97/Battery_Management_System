package com.project.batterymanagementsystem.adapters;

import android.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.project.batterymanagementsystem.fragments.HomeFragment;
import com.project.batterymanagementsystem.fragments.StatisticsFragment;

// Tab adaptor to include tabs on user interface

public class TabAdapter extends FragmentStatePagerAdapter {

    public static final int NUM_TABS = 2;

    public static final int TAB_HOME      = 0;
    public static final int TAB_CHARTS    = 1;

    private final SparseArray<Fragment> mFragments = new SparseArray<>(NUM_TABS);

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        try {
            super.restoreState(state, loader);
        } catch (IllegalStateException ignored) {
        }
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }

    @Override
    public androidx.fragment.app.Fragment getItem(int position) {
        switch (position) {
            case TAB_HOME:
                return HomeFragment.newInstance("1","2");
            case TAB_CHARTS:
                return StatisticsFragment.newInstance("1","2");
            default:
                return null;
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object item = super.instantiateItem(container, position);
        if (item instanceof Fragment) {
            mFragments.put(position, (Fragment) item);
        }
        return item;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        mFragments.remove(position);
        super.destroyItem(container, position, object);
    }
}
