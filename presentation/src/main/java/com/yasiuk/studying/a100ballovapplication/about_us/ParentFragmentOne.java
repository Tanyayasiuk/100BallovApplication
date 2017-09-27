package com.yasiuk.studying.a100ballovapplication.about_us;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasiuk.studying.a100ballovapplication.R;


public class ParentFragmentOne extends Fragment {

    public static Fragment newInstance(FragmentManager fragmentManager) {

        Fragment fragment = fragmentManager.findFragmentByTag(ParentFragmentOne.class.getName());
        ParentFragmentOne parentFragmentOne;

        if (fragment != null && fragment instanceof ParentFragmentOne) {
            parentFragmentOne = (ParentFragmentOne) fragment;
        } else {
            parentFragmentOne = new ParentFragmentOne();
        }

        Bundle args = new Bundle();
        parentFragmentOne.setArguments(args);
        return parentFragmentOne;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("SSS", "parent OnCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("SSS", "parent OnCreateView");
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("SSS", "parent OnViewCreated");

        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager_my);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getChildFragmentManager(),
                getContext().getApplicationContext()));
        Log.e("SSS", "adapter....");

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.sliding_tabs_my);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("SSS", " parent On Attach");
    }

    @Override
    public void onStart() {
        Log.e("SSS", "parent OnStart");
        super.onStart();
    }


    @Override
    public void onResume() {
        Log.e("SSS", "parent OnResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("SSS", "parent OnPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("SSS", "parent OnStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("SSS", "parent OnDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("SSS", "parent OnDetach");
    }
}


