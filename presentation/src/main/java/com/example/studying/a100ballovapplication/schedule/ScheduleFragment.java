package com.example.studying.a100ballovapplication.schedule;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.studying.a100ballovapplication.R;
import com.example.studying.a100ballovapplication.base.BaseFragment;
import com.example.studying.a100ballovapplication.databinding.FragmentScheduleBinding;

import java.util.ArrayList;
import java.util.List;


public class ScheduleFragment extends BaseFragment{
    //TODO Показывать выбранный класс

    private ScheduleFragmentViewModel sfViewModel;
    int classNum;
    public ScheduleFragment(){}

    public static ScheduleFragment newInstance(FragmentManager manager, int classNum){
        Fragment fragment = manager.findFragmentByTag(ScheduleFragment.class.getName());
        ScheduleFragment scheduleFragment;
        if(fragment != null && fragment instanceof ScheduleFragment){
            scheduleFragment = (ScheduleFragment) fragment;
        } else {
            scheduleFragment = new ScheduleFragment();
        }
        Bundle args = new Bundle();
        args.putInt("classNum", classNum);
        scheduleFragment.setArguments(args);
        return scheduleFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        sfViewModel = new ScheduleFragmentViewModel(getActivity(), getArguments().getInt("classNum"));
        this.viewModel = sfViewModel;
        this.classNum = getArguments().getInt("classNum");
        super.onCreate(savedInstanceState);
        Log.e("SSS", "Schedule Fragment - onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final FragmentScheduleBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false);
        binding.setView(sfViewModel);
        Log.e("SSS", "Schedule Fragment - onCreateView");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("SSS", "Schedule Fragment - onViewCreated");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.schedule_menu, menu);

        MenuItem item = menu.findItem(R.id.spinner_toolbar);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.add_class_list,R.layout.spinner_item_toolbar_my);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item_toolbar);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i != 0) {
                    int[] classes = {0, 4, 6, 7, 8, 9, 10, 11};
                    Fragment fragment = ScheduleFragment.newInstance(getActivity().getSupportFragmentManager(), classes[i]);
                    Log.e("SSS", "additionalSPINNER onItemSelected");
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container_basic, fragment).commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
