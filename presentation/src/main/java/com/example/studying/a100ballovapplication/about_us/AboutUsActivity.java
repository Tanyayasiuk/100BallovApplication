/*
package com.example.studying.a100ballovapplication.about_us;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.studying.a100ballovapplication.R;

public class AboutUsActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                AboutUsActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        Log.e("SSS", "AboutUsActivity created");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("SSS", "OnPAUSE - main");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("SSS", "OnDESTROY - main");
    }

    public static void showFragment(FragmentManager manager, Fragment fragment){
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.viewpager, fragment, fragment.getClass().getName());
        fragmentTransaction.addToBackStack(null); // добавление в историю (?????)
        fragmentTransaction.commit(); //"выполнить"
    }
}
*/
