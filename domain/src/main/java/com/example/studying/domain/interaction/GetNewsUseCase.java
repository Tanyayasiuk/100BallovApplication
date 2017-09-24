package com.example.studying.domain.interaction;


import com.example.studying.data.entity.NewsData;
import com.example.studying.data.net.RestService;
import com.example.studying.domain.entity.News;
import com.example.studying.domain.interaction.base.UseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class GetNewsUseCase extends UseCase<Void, List<News>>{

    @Inject
    public GetNewsUseCase() {}

    @Override
    protected Observable<List<News>> buildUseCase(Void aVoid) {
        return RestService.getInstance().getNews()
                .map(new Function<List<NewsData>, List<News>>() {
                    @Override
                    public List<News> apply(@NonNull List<NewsData> newsDatas) throws Exception {
                        List<News> newsList = new ArrayList<>();
                        for(NewsData newsItem: newsDatas){
                            News news = new News();
                            news.setTitle(newsItem.getNewstitle());
                            news.setNewsDate(newsItem.getNewsdate());
                            news.setText(newsItem.getNewstext());
                            newsList.add(news);
                        }
                        return newsList;
                    }
                });
    }
}
