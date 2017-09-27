package com.yasiuk.studying.a100ballovapplication.contacts;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasiuk.studying.a100ballovapplication.R;
import com.yasiuk.studying.a100ballovapplication.base.BaseFragment;
import com.yasiuk.studying.a100ballovapplication.databinding.FragmentContactsBinding;



public class ContactsFragment extends BaseFragment {

    private ContactsFragmentViewModel cfViewModel;

    public ContactsFragment() {
    }


    public static ContactsFragment newInstance(FragmentManager manager) {

        Fragment fragment = manager.findFragmentByTag(ContactsFragment.class.getName());
        ContactsFragment contactsFragment;
        if(fragment != null && fragment instanceof ContactsFragment){
            contactsFragment = (ContactsFragment) fragment;
        } else {
            contactsFragment = new ContactsFragment();
        }
        return contactsFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        cfViewModel = new ContactsFragmentViewModel(getActivity());
        this.viewModel = cfViewModel;
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentContactsBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_contacts, container, false);
        binding.setView(cfViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

