package com.yasiuk.studying.a100ballovapplication.schedule;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.yasiuk.studying.a100ballovapplication.R;


public class ChooseFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog, null, false);

        Spinner spin;
        spin = (Spinner)view.findViewById(R.id.spinner_dialog);
        Log.e("SSS", "Spinner is set" + spin.toString());
        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(getContext(), R.array.class_list, R.layout.spinner_item_my);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item_my);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                     int[] classes = {0, 4, 6, 7, 8, 9, 10, 11};
                     Fragment fragment = ScheduleFragment.newInstance(getActivity().getSupportFragmentManager(), classes[i]);
                     FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                     fragmentTransaction.replace(R.id.container_basic, fragment).commit();
                     dismiss();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.e("SSS", "Nothing SELECTED");
            }
        });

        builder.setMessage("Выберите класс:").setView(view);
        return builder.create();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
