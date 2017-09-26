package com.example.studying.a100ballovapplication.news;

import android.app.Activity;
import android.databinding.ObservableField;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.studying.a100ballovapplication.MyApplication;
import com.example.studying.a100ballovapplication.R;
import com.example.studying.a100ballovapplication.base.BaseViewModel;
import com.example.studying.a100ballovapplication.schedule.ItemViewModel;
import com.example.studying.a100ballovapplication.schedule.ScheduleRVAdapter;
import com.example.studying.domain.entity.News;
import com.example.studying.domain.entity.OkDomain;
import com.example.studying.domain.entity.Schedule;
import com.example.studying.domain.interaction.GetNewsUseCase;
import com.example.studying.domain.interaction.GetScheduleUseCase;
import com.example.studying.domain.interaction.PostNewsUseCase;
import com.example.studying.domain.interaction.PushUseCase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;


public class NewsFragmentViewModel implements BaseViewModel {

    private Activity activity;
    public enum STATE {PROGRESS, DATA}
    //TODO Добавить механизм установки isAdmin(true/false)
    public boolean isAdmin = false;
    private List<NewsItemViewModel> itemsList;
    public ObservableField<STATE> state = new ObservableField<>(STATE.PROGRESS);
    public ObservableField<String> message = new ObservableField<>();
    ImageButton sendNews;

    @Inject
    public GetNewsUseCase useCase;

    @Inject
    public PostNewsUseCase postUseCase;

    @Inject
    public PushUseCase push;

    public NewsFragmentViewModel(Activity activity) {
        MyApplication.appComponent.inject(this);
        this.activity = activity;
    }

    @Override
    public void init() {Log.e("SSS", "NewsFragViewMOdel - init");}

    @Override
    public void resume() {
        final RecyclerView recyclerView = (RecyclerView)activity.findViewById(R.id.news_recycler_view);
        final LinearLayoutManager manager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(manager);

        refreshRecyclerView(recyclerView);

        sendNews = (ImageButton) activity.findViewById(R.id.send_news_button);
        sendNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if(!message.get().equals("")){
                    final News news = new News();
                    news.setText(message.get());

                    postUseCase.execute(news, new DisposableObserver<OkDomain>() {
                        @Override
                        public void onNext(@NonNull OkDomain okDomain) {
                            message.set("");
                            push.execute(news, new DisposableObserver<OkDomain>() {
                                @Override
                                public void onNext(@NonNull OkDomain okDomain) {
                                    Log.e("SSS", "PUSH - OnNext");
                                    refreshRecyclerView(recyclerView);
                                    Log.e("SSS", "refresh after push");
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    Log.e("SSS", "PUSH - OnERROR: " + e.getLocalizedMessage());
                                }

                                @Override
                                public void onComplete() { push.dispose();
                                }
                            });
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e("SSS", e.getLocalizedMessage());
                        }

                        @Override
                        public void onComplete() {
                            postUseCase.dispose();
                        }
                    });
                }

            }
        });
    }

    @Override
    public void pause() {Log.e("SSS", "NewsFragViewMOdel -  Pause");}

    @Override
    public void release() {useCase.dispose();}


    private void refreshRecyclerView(final RecyclerView recyclerView){
        useCase.execute(null, new DisposableObserver<List<News>>() {
            @Override
            public void onNext(@NonNull List<News> newses) {
                itemsList  = new ArrayList<>(newses.size());
                for(News news: newses){
                    try {
                        NewsItemViewModel item = new NewsItemViewModel(news.getTitle(), news.getNewsDate(), news.getText());
                        itemsList.add(item);
                    } catch (ParseException ex){
                        Log.e("SSS", ex.getLocalizedMessage());
                    }
                }
                NewsRVAdapter adapter = new NewsRVAdapter(activity, itemsList);
                recyclerView.setAdapter(adapter);
                state.set(STATE.DATA);
            }

            @Override
            public void onError(@NonNull Throwable e) {Log.e("SSS", e.getLocalizedMessage());}

            @Override
            public void onComplete() {
            }
        });

    }

}
