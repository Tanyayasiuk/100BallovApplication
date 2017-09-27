package com.yasiuk.studying.a100ballovapplication.books;

import android.app.Activity;

import com.yasiuk.studying.a100ballovapplication.base.BaseViewModel;


public class BooksFragmentViewModel implements BaseViewModel {

    private Activity activity;

    public BooksFragmentViewModel(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void init() {

    }

    @Override
    public void release() {

    }

    @Override
    public void resume() {
        // Скачивать и показывать pdf
        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://che.org.il/wp-content/uploads/2016/12/pdf-sample.pdf")));
        //permission to access external storage (?)
    }

    @Override
    public void pause() {

    }
}
