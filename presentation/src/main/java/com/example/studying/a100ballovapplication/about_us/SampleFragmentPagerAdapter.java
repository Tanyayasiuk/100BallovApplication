package com.example.studying.a100ballovapplication.about_us;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.studying.a100ballovapplication.R;


public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[];
    private Context context;
    private FragmentManager fm;


    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        this.fm = fm;
        tabTitles = new String[]{context.getString(R.string.teacher1), context.getString(R.string.teacher2)};
    }


    @Override
    public Fragment getItem(int position) {
        return FragmentOne.newInstance(fm, tabTitles[position]);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}
