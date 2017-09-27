package com.yasiuk.studying.a100ballovapplication.about_us;

import android.app.Activity;
import android.databinding.ObservableField;
import android.util.Log;

import com.yasiuk.studying.a100ballovapplication.base.BaseViewModel;
import com.yasiuk.studying.domain.entity.Teacher;
import com.yasiuk.studying.domain.interaction.GetTeacherInfoUseCase;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class FragmentViewModel implements BaseViewModel {

    private Activity activity;
    private String teachersName;
    public enum STATE {PROGRESS, DATA};

    //public WebView mWebView;

    public FragmentViewModel(Activity activity, String teachersName){
        this.activity = activity;
        this.teachersName = teachersName;
    }

    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> surname = new ObservableField<>("");
    public ObservableField<String> education = new ObservableField<>("");
    public ObservableField<String> comment = new ObservableField<>("");
    public ObservableField<String> imageUrl = new ObservableField<>("");
    public ObservableField<String> additional = new ObservableField<>("");
    public ObservableField<String> lessontype = new ObservableField<>("");
    public ObservableField<String> classes = new ObservableField<>("");
    public ObservableField<STATE> state = new ObservableField<>(STATE.PROGRESS);
    private GetTeacherInfoUseCase useCase = new GetTeacherInfoUseCase();

    @Override
    public void init() {

    }

    @Override
    public void release() {

    }


    @Override
    public void resume() {

       /* For html page and fragment_two usage
        * (change binding and layout in FragmnetOne onCreateView*//*
        mWebView = activity.findViewById(R.id.webView);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        // указываем страницу загрузки
        mWebView.loadUrl("https://api.backendless.com/D98411AD-4158-F507-FF34-CC7C7669CF00/A6BF2F37-9F1B-437A-FF10-82EE9CECF100/files/Ivan.html");

*/
        // Getting the teacher's profile to show depending on their name
        useCase.execute(teachersName, new DisposableObserver<Teacher>() {
            @Override
            public void onNext(@NonNull Teacher teachers) {
                name.set(teachers.getName());
                surname.set(teachers.getSurname());
                education.set(teachers.getEducation());
                additional.set(teachers.getAdditional());
                lessontype.set(teachers.getLessontype());
                classes.set(teachers.getClasses());
                comment.set(teachers.getComment());
                imageUrl.set(teachers.getImageUrl());
                state.set(STATE.DATA);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                {
                    Log.e("SSS1", "alarma! "+ e.getLocalizedMessage());
                }
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

    /* //For html-page
    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
    }*/

}
