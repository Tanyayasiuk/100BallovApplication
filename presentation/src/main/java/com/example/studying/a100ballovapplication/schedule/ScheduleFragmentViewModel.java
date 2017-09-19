package com.example.studying.a100ballovapplication.schedule;

import android.app.Activity;
import android.databinding.ObservableField;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import com.example.studying.a100ballovapplication.R;
import com.example.studying.a100ballovapplication.base.BaseViewModel;
import com.example.studying.domain.entity.Schedule;
import com.example.studying.domain.interaction.GetScheduleUseCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;


public class ScheduleFragmentViewModel implements BaseViewModel {

    private Activity activity;
    private int classNum;
    public enum STATE {PROGRESS, DATA};

    public ScheduleFragmentViewModel(Activity activity, int classNum) {
        this.activity = activity;
        this.classNum = classNum;
    }

    public List<Schedule> scheduleList;
    public String itemTitle;
    private GetScheduleUseCase useCase = new GetScheduleUseCase();
    private List<ItemViewModel> itemsList = new ArrayList<>();
    public ObservableField<STATE> state = new ObservableField<>(STATE.PROGRESS);

    @Override
    public void init() {
        itemTitle = String.valueOf(classNum) + " класс";
    }

    @Override
    public void release() {

    }

    @Override
    public void resume() {
        final RecyclerView recyclerView = (RecyclerView)activity.findViewById(R.id.schedule_recycler_view);
        final LinearLayoutManager manager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(manager);

        useCase.execute(classNum, new DisposableObserver<List<Schedule>>() {
            @Override
            public void onNext(@NonNull List<Schedule> schedules) {
                scheduleList = schedules;
                ArrayList<Integer> days = new ArrayList<>();
                for(Schedule s: schedules){
                    if (!days.contains(s.getDay())){
                        days.add(s.getDay());
                    }
                }
                Collections.sort(days);
                Log.e("SSS", "Days: " + days.toString());

                itemsList = new ArrayList<>(days.size());
                for (int i = 0; i < days.size(); i++) {
                    StringBuilder element = new StringBuilder();
                    for (int j = 0; j < scheduleList.size(); j++) {
                        if(days.get(i) == scheduleList.get(j).getDay()){
                            element.append(scheduleList.get(j).getTime());
                            element.append("  ");
                            element.append(scheduleList.get(j).getSubject());
                            element.append("\n");
                        }
                    }
                    itemsList.add(new ItemViewModel(days.get(i),
                            element.toString()));
                }

                ScheduleRVAdapter adapter = new ScheduleRVAdapter(activity, itemsList);
                recyclerView.setAdapter(adapter);
                state.set(STATE.DATA);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("SSS", "Error: " + e);
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
