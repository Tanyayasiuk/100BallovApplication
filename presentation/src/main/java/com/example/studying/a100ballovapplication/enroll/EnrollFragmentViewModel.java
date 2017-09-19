package com.example.studying.a100ballovapplication.enroll;

import android.app.Activity;
import android.databinding.ObservableField;

import com.example.studying.a100ballovapplication.base.BaseViewModel;


public class EnrollFragmentViewModel implements BaseViewModel {

    private Activity activity;
    public enum STATE {PROGRESS, DATA};

    public ObservableField<STATE> state = new ObservableField<>(STATE.PROGRESS);

    public EnrollFragmentViewModel(Activity activity) {
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

        /* Здесь будет что-то вроде EnrollUseCase
        * Данные со всех полей упаковываются в профиль студента и отправляются на сервер (?)
        * точнее, отправляются в письме ответственным за запись*/

    }

    @Override
    public void pause() {

    }

}
