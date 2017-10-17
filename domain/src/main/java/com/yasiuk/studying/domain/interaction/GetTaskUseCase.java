package com.yasiuk.studying.domain.interaction;

import com.yasiuk.studying.data.entity.TaskData;
import com.yasiuk.studying.data.net.RestService;
import com.yasiuk.studying.domain.interaction.base.UseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


public class GetTaskUseCase extends UseCase<Integer, String> {

    @Inject
    public GetTaskUseCase() {}

    @Override
    protected Observable<String> buildUseCase(Integer integer) {
        return RestService.getInstance().getTask(integer)
                .map(new Function<List<TaskData>, String>() {
                    @Override
                    public String apply(@NonNull List<TaskData> taskDatas) throws Exception {
                        StringBuilder bookTitles = new StringBuilder();
                        for(TaskData tasks: taskDatas){
                            bookTitles.append(tasks.getTask());
                            bookTitles.append("\n");
                        }
                        return bookTitles.toString();
                    }
                });

    }
}

