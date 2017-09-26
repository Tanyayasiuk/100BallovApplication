package com.example.studying.a100ballovapplication.my_profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableField;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.studying.a100ballovapplication.R;
import com.example.studying.a100ballovapplication.base.BaseViewModel;
import com.example.studying.a100ballovapplication.books.BooksFragment;
import com.example.studying.domain.entity.Schedule;
import com.example.studying.domain.interaction.GetScheduleUseCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

import static com.example.studying.a100ballovapplication.base.Defaults.CLASS_NUM;
import static com.example.studying.a100ballovapplication.base.Defaults.KEY_USER_EMAIL;
import static com.example.studying.a100ballovapplication.base.Defaults.KEY_USER_LOGIN;
import static com.example.studying.a100ballovapplication.base.Defaults.SHARED_PREFS_NAME;


public class MyProfileFragmentViewModel implements BaseViewModel {

    private AppCompatActivity activity;
    public enum STATE {PROGRESS, DATA};
    int classNum;
    public ObservableField<STATE> state = new ObservableField<>(STATE.PROGRESS);

    public MyProfileFragmentViewModel(AppCompatActivity activity){
        this.activity = activity;
    }

    SharedPreferences preferences;
    private GetScheduleUseCase useCase = new GetScheduleUseCase();

    public ObservableField<String> nickname = new ObservableField<>("");
    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> schedule = new ObservableField<>("");
    public ObservableField<String> books = new ObservableField<>("");
    public ObservableField<String> imageUrl = new ObservableField<>("");
    public ObservableField<String> hometask = new ObservableField<>("");
    public ObservableField<String> additional = new ObservableField<>("");
    public ObservableField<String> classNumberString = new ObservableField<>("");

    @Override
    public void init() {
        preferences = activity.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        classNum = preferences.getInt(CLASS_NUM, 0);
    }

    @Override
    public void release() {}


    @Override
    public void resume() {
        // Getting the student's profile to show depending on their name
        email.set(preferences.getString(KEY_USER_EMAIL, null));
        nickname.set(preferences.getString(KEY_USER_LOGIN, null));
        classNumberString.set(String.valueOf(classNum).concat(" класс"));
        books.set("Учебник. Белорусский язык " + classNum + " класс.");

        getUsersSchedule(classNum);

        //TODO Сделать useCase на получение ДЗ
        hometask.set("Параграф №4\nУпражнения 1 - 3.\nВопросы после параграфа.");
        state.set(STATE.DATA);

        TextView myBooks = (TextView) activity.findViewById(R.id.student_books);
        myBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BooksFragment fragment = BooksFragment.newInstance(activity.getSupportFragmentManager());
                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment, fragment.getClass().getName());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void pause() {}

    private void getUsersSchedule (int classNum){

        //TODO Убрать вот это, и заменить на что-то приличное:
        final String[] weekDays = {"", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};

        useCase.execute(classNum, new DisposableObserver<List<Schedule>>() {
            @Override
            public void onNext(@NonNull List<Schedule> schedules) {
                ArrayList<Integer> days = new ArrayList<>();
                for(Schedule s: schedules){
                    if (!days.contains(s.getDay())){
                        days.add(s.getDay());
                    }
                }
                Collections.sort(days);
                //TODO Доделать. Вообще, конечно, лучше сделать recyclerview... но это столько доп. текста....
                StringBuilder element = new StringBuilder();
                for (int i = 0; i < days.size(); i++) {
                    element.append("\n");
                    element.append(weekDays[days.get(i)]);
                    element.append("\n\n");
                    for(int j = 0; j < schedules.size(); j++){
                        if(days.get(i) == schedules.get(j).getDay()){
                            element.append(schedules.get(j).getTime());
                            element.append("  ");
                            element.append(schedules.get(j).getSubject());
                            element.append("\n");
                            Log.e("SSS", element.toString());
                        }
                    }
                }
                schedule.set(element.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("SSS", e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                useCase.dispose();
            }
        });

    }



}
