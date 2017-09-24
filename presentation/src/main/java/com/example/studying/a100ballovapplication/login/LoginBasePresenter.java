package com.example.studying.a100ballovapplication.login;


public interface LoginBasePresenter {

    //Методы, к которым будет иметь доступ активити
    //полиморфизм, ё-моё
    void onLoginButtonClick(String username, String password);
    void onPause();
    void onResume();
    void onRelease();

}
