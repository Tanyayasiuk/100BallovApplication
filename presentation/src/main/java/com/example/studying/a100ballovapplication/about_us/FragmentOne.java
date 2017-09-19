package com.example.studying.a100ballovapplication.about_us;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.studying.a100ballovapplication.R;
import com.example.studying.a100ballovapplication.base.BaseFragment;

import com.example.studying.a100ballovapplication.databinding.FragmentOneBinding;


public class FragmentOne extends BaseFragment {

    public static final String ARG_NAME = "ARG_NAME";
    private FragmentViewModel fragmentViewModel;

    public FragmentOne() {
    }

    public static FragmentOne newInstance(FragmentManager manager, String teachersName) {

        FragmentOne fragmentOne = new FragmentOne();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, teachersName);
        fragmentOne.setArguments(args);
        Log.e("SSS", "FragmentOne new Instance");
        return fragmentOne;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        fragmentViewModel = new FragmentViewModel(getActivity(), getArguments().getString(ARG_NAME));
        this.viewModel = fragmentViewModel;
        super.onCreate(savedInstanceState);
        Log.e("SSS", "FragmentOne onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentOneBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_one, container, false);
        binding.setView(fragmentViewModel);
        Log.e("SSS", "FragmentOne onCreateView");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("SSS", "FragmentOne onView Created");
    }

    @BindingAdapter({"android:src"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("SSS", "FragmentOne onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("SSS", "FragmentOne onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("SSS", "FragmentOne onDestroy");
    }
}
