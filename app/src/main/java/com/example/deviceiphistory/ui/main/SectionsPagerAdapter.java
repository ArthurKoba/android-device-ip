package com.example.deviceiphistory.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.deviceiphistory.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{
            R.string.tab_main, R.string.tab_history, R.string.tab_test
    };
    private final Context context;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        this.context = context;
    }

    @NonNull
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
//            case 0: return MainFragment.newInstance(this.context);
            case 1: return HistoryFragment.newInstance(context);
            case 2: return TestFragment.newInstance();
            default: return MainFragment.newInstance(context);
        }
    }

    @Nullable
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }

    public int getCount() {
        return 3;
    }
}