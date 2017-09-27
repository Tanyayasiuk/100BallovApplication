package com.yasiuk.studying.a100ballovapplication.news;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableField;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.yasiuk.studying.a100ballovapplication.MyApplication;
import com.yasiuk.studying.a100ballovapplication.R;
import com.yasiuk.studying.a100ballovapplication.base.BaseViewModel;
import com.yasiuk.studying.domain.entity.News;
import com.yasiuk.studying.domain.entity.OkDomain;
import com.yasiuk.studying.domain.interaction.GetNewsUseCase;
import com.yasiuk.studying.domain.interaction.PostNewsUseCase;
import com.yasiuk.studying.domain.interaction.PushUseCase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

import static com.yasiuk.studying.a100ballovapplication.base.Defaults.ADMIN;
import static com.yasiuk.studying.a100ballovapplication.base.Defaults.KEY_USER_EMAIL;
import static com.yasiuk.studying.a100ballovapplication.base.Defaults.SHARED_PREFS_NAME;


public class NewsFragmentViewModel implements BaseViewModel {

    private Activity activity;
    public enum STATE {PROGRESS, DATA}
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
        SharedPreferences preferences = activity.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        if(preferences.getString(KEY_USER_EMAIL, null)!= null){
            if(preferences.getString(KEY_USER_EMAIL, null).equals(ADMIN))  isAdmin = true;
        }

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
    public void release() { }


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
            public void onComplete() {useCase.dispose();
            }
        });

    }

}
