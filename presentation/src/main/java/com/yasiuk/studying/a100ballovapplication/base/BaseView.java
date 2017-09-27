package com.yasiuk.studying.a100ballovapplication.base;


public interface BaseView {

     void showProgress();
     void dismissProgress();
     void showError(String error);
     void goToMainActivity ();
     void askPermission();
     void logIn(String login);
     void showToast(int message);


}
