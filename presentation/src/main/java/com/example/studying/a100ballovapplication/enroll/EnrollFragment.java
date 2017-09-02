package com.example.studying.a100ballovapplication.enroll;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studying.a100ballovapplication.R;
import com.example.studying.a100ballovapplication.base.BaseFragment;
import com.example.studying.a100ballovapplication.databinding.FragmentEnrollBinding;

public class EnrollFragment extends BaseFragment {

    private EnrollFragmentViewModel efViewModel;

    public EnrollFragment() {
    }

    public static EnrollFragment newInstance(FragmentManager manager) {

        Fragment fragment = manager.findFragmentByTag(EnrollFragment.class.getName());
        EnrollFragment enrollFragment;

        if(fragment != null && fragment instanceof EnrollFragment){
            enrollFragment = (EnrollFragment) fragment;
        } else {
            enrollFragment = new EnrollFragment();
        }
        Bundle args = new Bundle();
        enrollFragment.setArguments(args);
        return enrollFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        efViewModel = new EnrollFragmentViewModel(getActivity());
        this.viewModel = efViewModel;
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentEnrollBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_enroll, container, false);
        binding.setView(efViewModel);

        // Адаптер для выпадающего списка
        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(getContext(), R.array.class_list, R.layout.spinner_item_my);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item_my);

        binding.spinner.setAdapter(adapter);
        binding.spinner.setAlpha(0.7f);

        // обработчик нажатия
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO Запихнуть сюда логику onItemSelected (класс)

                /* При поворачивании экрана на строку с цветом - NPE ((
               ((TextView) adapterView.getChildAt(0)).setTextColor(Color.DKGRAY);
                ((TextView) adapterView.getChildAt(0)).setTextSize(14);*/
                //Toast.makeText(getContext(), "Position = " + i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
