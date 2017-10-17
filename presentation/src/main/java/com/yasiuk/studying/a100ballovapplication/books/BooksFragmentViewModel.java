package com.yasiuk.studying.a100ballovapplication.books;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.yasiuk.studying.a100ballovapplication.R;
import com.yasiuk.studying.a100ballovapplication.base.BaseViewModel;
import com.yasiuk.studying.domain.entity.Book;
import com.yasiuk.studying.domain.interaction.GetBooksUseCase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

import static com.yasiuk.studying.a100ballovapplication.base.Defaults.CLASS_NUM;
import static com.yasiuk.studying.a100ballovapplication.base.Defaults.SHARED_PREFS_NAME;

public class BooksFragmentViewModel implements BaseViewModel {

    private AppCompatActivity activity;


    public BooksFragmentViewModel(AppCompatActivity activity) {
        this.activity = activity;
    }

    private GetBooksUseCase useCase = new GetBooksUseCase();
    public ObservableField<String> tasks = new ObservableField<>("");
    private int classNum;
    private SharedPreferences preferences;
    private List<BooksItemViewModel> itemsList;
    public enum STATE {PROGRESS, DATA}
    public ObservableField<STATE> state = new ObservableField<>(STATE.PROGRESS);


    @Override
    public void init() {
        preferences = activity.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        classNum = preferences.getInt(CLASS_NUM, 0);
    }

    @Override
    public void release() {

    }

    @Override
    public void resume() {
        // Скачивать и показывать pdf
        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://che.org.il/wp-content/uploads/2016/12/pdf-sample.pdf")));
        //permission to access external storage (?)

        final RecyclerView recyclerView = (RecyclerView)activity.findViewById(R.id.books_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(manager);

        useCase.execute(classNum, new DisposableObserver<List<Book>>() {
            @Override
            public void onNext(@NonNull List<Book> books) {
                itemsList  = new ArrayList<>(books.size());
                for(Book book: books){
                    try {
                        BooksItemViewModel item = new BooksItemViewModel(book.getName(), book.getPublicUrl());
                        itemsList.add(item);
                    } catch (ParseException ex){
                        Log.e("SSS", ex.getLocalizedMessage());
                    }
                }
                BooksRVAdapter adapter = new BooksRVAdapter(activity, itemsList);
                recyclerView.setAdapter(adapter);
                state.set(STATE.DATA);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("SSS", e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void pause() {
        useCase.dispose();
    }
}
