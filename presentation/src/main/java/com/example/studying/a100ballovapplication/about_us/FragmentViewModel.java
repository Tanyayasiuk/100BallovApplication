package com.example.studying.a100ballovapplication.about_us;

import android.app.Activity;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.studying.a100ballovapplication.base.BaseViewModel;
import com.example.studying.domain.entity.Teacher;
import com.example.studying.domain.interaction.GetTeacherInfoUseCase;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class FragmentViewModel implements BaseViewModel {

    private Activity activity;
    private String teachersName;
    public enum STATE {PROGRESS, DATA};


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

        // Getting the teacher's profile to show depending on their name
        useCase.execute(teachersName, new DisposableObserver<Teacher>() {
            @Override
            public void onNext(@NonNull Teacher teachers) {
                name.set(teachers.getName());
                Log.e("SSS1", String.valueOf(name));
                surname.set(teachers.getSurname());
                Log.e("SSS1", surname.toString());
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

}
