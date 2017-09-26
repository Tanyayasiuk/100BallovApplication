package com.example.studying.domain.interaction;

import android.util.Log;

import com.example.studying.data.entity.Message;
import com.example.studying.data.entity.NewsData;
import com.example.studying.data.entity.RegisterResponse;
import com.example.studying.data.net.RestAPI;
import com.example.studying.data.net.RestService;
import com.example.studying.domain.entity.News;
import com.example.studying.domain.entity.OkDomain;
import com.example.studying.domain.interaction.base.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

import static com.example.studying.data.entity.Message.TITLE;


public class PostNewsUseCase extends UseCase<News, OkDomain> {

    @Inject
    public PostNewsUseCase() {}

    @Override
    protected Observable<OkDomain> buildUseCase( News news) {
        return RestService.getInstance().addNews(convertToNewsData(news))
                .map(new Function<Void, OkDomain>() {
                    @Override
                    public OkDomain apply(@NonNull Void aVoid) throws Exception {
                        return new OkDomain();
                    }
                });
    }


    private NewsData convertToNewsData(News news){
        NewsData nd = new NewsData();
        nd.setNewsdate(String.valueOf(System.currentTimeMillis()));
        nd.setNewstitle(TITLE);
        nd.setNewstext(news.getText());
        Log.e("SSS", "NewsData: " + nd.getNewstext() + " " + nd.getNewstitle() + " " + nd.getNewsdate());
        return nd;
    }

}
