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

import static com.example.studying.data.entity.Defaults.APP_NAME;
import static com.example.studying.data.entity.Message.CONTENT_TEXT;
import static com.example.studying.data.entity.Message.CONTENT_TITLE;
import static com.example.studying.data.entity.Message.TICKER;
import static com.example.studying.data.entity.Message.TITLE;

public class PushUseCase extends UseCase<News, OkDomain> {

    @Inject
    public PushUseCase() {}

    @Override
    protected Observable<OkDomain> buildUseCase(News news) {
        return RestService.getInstance().publish(convertToPush(news))
                .map(new Function<RegisterResponse, OkDomain>() {
                    @Override
                    public OkDomain apply(@NonNull RegisterResponse registerResponse) throws Exception {
                        return new OkDomain();
                    }
                });
    }

    private Message convertToPush(News news){
        Message message = new Message();
        message.setMessage(news.getText());
        message.setPushPolicy("PUSH");
        HashMap<String, String> headers = new HashMap<>();
        headers.put(TICKER, APP_NAME);
        headers.put(CONTENT_TITLE, TITLE);
        headers.put(CONTENT_TEXT, news.getText());
        message.setHeaders(headers);
        return message;
    }
}
