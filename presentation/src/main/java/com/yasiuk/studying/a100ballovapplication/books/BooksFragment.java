package com.yasiuk.studying.a100ballovapplication.books;

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
import com.yasiuk.studying.a100ballovapplication.databinding.FragmentBooksBinding;

import static com.yasiuk.studying.a100ballovapplication.base.Defaults.KEY_FRAGMENT;

/* The possibility to view and download parts of textbooks in pdf
* from the server according to user's class
* is going to be implemented here*/

public class BooksFragment extends BaseFragment {

    private BooksFragmentViewModel booksViewModel;

    public BooksFragment() {
    }

    public static BooksFragment newInstance(FragmentManager manager){

        Fragment fragment = manager.findFragmentByTag(BooksFragment.class.getName());
        BooksFragment booksFragment;

        if(fragment != null && fragment instanceof BooksFragment){
            booksFragment = (BooksFragment) fragment;
        } else {
            booksFragment = new BooksFragment();
        }
        Bundle args = new Bundle();
        args.putString(KEY_FRAGMENT, "Книги");
        booksFragment.setArguments(args);
        return booksFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        booksViewModel = new BooksFragmentViewModel(getActivity());
        this.viewModel = booksViewModel;
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentBooksBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_books, container, false);
        binding.setView(booksViewModel);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
