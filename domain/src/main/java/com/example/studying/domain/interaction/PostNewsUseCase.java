package com.example.studying.domain.interaction;

import com.example.studying.data.entity.Message;
import com.example.studying.data.entity.RegisterResponse;
import com.example.studying.data.net.RestService;
import com.example.studying.domain.entity.News;
import com.example.studying.domain.entity.OkDomain;
import com.example.studying.domain.interaction.base.UseCase;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import retrofit2.http.POST;


public class PostNewsUseCase extends UseCase<News, OkDomain> {

    //TODO Add posting a message to the news (not only push)

    @Inject
    public PostNewsUseCase() {}

    @Override
    protected Observable<OkDomain> buildUseCase(News news) {
        return RestService.getInstance().publish(convert(news))
                .map(new Function<RegisterResponse, OkDomain>() {
                    @Override
                    public OkDomain apply(@NonNull RegisterResponse registerResponse) throws Exception {
                        return new OkDomain();
                    }
                });
    }

    private Message convert(News news){
        Message message = new Message();
        message.setMessage(news.getText());
        message.setPushPolicy("PUSH");
        HashMap<String, String> headers = new HashMap<>();
        headers.put("android-ticker-text", "Ticker");
        headers.put("android-content-title", "Новости");
        headers.put("android-content-text", news.getText());
        message.setHeaders(headers);
        return message;
    }
}
