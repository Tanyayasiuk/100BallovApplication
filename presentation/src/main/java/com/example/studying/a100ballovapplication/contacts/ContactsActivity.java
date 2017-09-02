package com.example.studying.a100ballovapplication.contacts;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.studying.a100ballovapplication.R;
import com.example.studying.a100ballovapplication.base.BaseFragmentActivity;

public class ContactsActivity extends BaseFragmentActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_contacts);
    }

    public static void showFragment(FragmentManager manager, Fragment fragment){
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, fragment.getClass().getName());
        //fragmentTransaction.addToBackStack(null); // добавление в историю (?????)
        fragmentTransaction.commit(); //"выполнить"
    }
}
