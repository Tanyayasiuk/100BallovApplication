package com.yasiuk.studying.a100ballovapplication.my_profile;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yasiuk.studying.a100ballovapplication.R;
import com.yasiuk.studying.a100ballovapplication.base.BaseFragment;
import com.yasiuk.studying.a100ballovapplication.databinding.FragmentProfileBinding;

import static com.yasiuk.studying.a100ballovapplication.base.Defaults.KEY_FRAGMENT;

public class MyProfileFragment extends BaseFragment {

    private MyProfileFragmentViewModel fragmentModel;
    public static String title = "Мой профиль";

    public MyProfileFragment() {
    }

    public static MyProfileFragment newInstance(FragmentManager manager) {
        Fragment fragment = manager.findFragmentByTag(MyProfileFragment.class.getName());
        MyProfileFragment myProfileFragment;

        if(fragment != null && fragment instanceof MyProfileFragment){
            myProfileFragment = (MyProfileFragment) fragment;
        } else {
            myProfileFragment = new MyProfileFragment();
        }
        Bundle args = new Bundle();
        args.putString(KEY_FRAGMENT, title);
        myProfileFragment.setArguments(args);
        return myProfileFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        fragmentModel = new MyProfileFragmentViewModel((AppCompatActivity) getActivity());
        this.viewModel = fragmentModel;
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentProfileBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_profile, container, false);
        binding.setView(fragmentModel);
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
        //Log.e("SSS", "myProfileFragment onResume");
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
