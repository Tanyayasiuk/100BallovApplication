package com.example.studying.a100ballovapplication.my_profile;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.studying.a100ballovapplication.R;
import com.example.studying.a100ballovapplication.base.BaseFragment;
import com.example.studying.a100ballovapplication.databinding.FragmentProfileBinding;


public class MyProfileFragment extends BaseFragment {

    public static final String ARG_NAME = "ARG_NAME";
    private MyProfileFragmentViewModel fragmentModel;

    public MyProfileFragment() {
    }

    public static MyProfileFragment newInstance(FragmentManager manager) {
        MyProfileFragment myProfileFragment = new MyProfileFragment();
        Log.e("SSS", "MyProf fragment new Instance");
        return myProfileFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        fragmentModel = new MyProfileFragmentViewModel(getActivity());
        this.viewModel = fragmentModel;
        super.onCreate(savedInstanceState);
        Log.e("SSS", "myProfileFragment onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentProfileBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_profile, container, false);
        binding.setView(fragmentModel);
        Log.e("SSS", "myProfileFragment onCreateView");

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("SSS", "myProfileFragment onView Created");
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
        Log.e("SSS", "myProfileFragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("SSS", "myProfileFragment onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("SSS", "myProfileFragment onDestroy");
    }




}
