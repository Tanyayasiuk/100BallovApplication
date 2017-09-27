package com.yasiuk.studying.a100ballovapplication.news;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasiuk.studying.a100ballovapplication.R;
import com.yasiuk.studying.a100ballovapplication.base.BaseFragment;
import com.yasiuk.studying.a100ballovapplication.databinding.FragmentNewsBinding;

import static com.yasiuk.studying.a100ballovapplication.base.Defaults.KEY_FRAGMENT;


public class NewsFragment extends BaseFragment{

    private NewsFragmentViewModel nfViewModel;
    public NewsFragment(){}

    public static NewsFragment newInstance(FragmentManager manager){
        Fragment fragment = manager.findFragmentByTag(NewsFragment.class.getName());
        NewsFragment newsFragment;
        if(fragment != null && fragment instanceof NewsFragment){
            newsFragment = (NewsFragment) fragment;
        } else {
            newsFragment = new NewsFragment();
        }

        Bundle args = new Bundle();
        args.putString(KEY_FRAGMENT, "Новости");
        newsFragment.setArguments(args);
        return newsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        nfViewModel = new NewsFragmentViewModel(getActivity());
        this.viewModel = nfViewModel;
        super.onCreate(savedInstanceState);
        Log.e("SSS", "NEws Fragment - onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final FragmentNewsBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);
        binding.setView(nfViewModel);
        Log.e("SSS", "News Fragment - onCreateView");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("SSS", "News Fragment - onViewCreated");
    }



}
