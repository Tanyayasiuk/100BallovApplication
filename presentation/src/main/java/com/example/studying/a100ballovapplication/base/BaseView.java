package com.example.studying.a100ballovapplication.base;


import com.example.studying.domain.entity.DomainModel;

public interface BaseView {

     void showProgress();
     void dismissProgress();
     void showError(String error);
     void goToMainActivity ();
     void logIn(String login, String password);
     void showToast(int message);


}
