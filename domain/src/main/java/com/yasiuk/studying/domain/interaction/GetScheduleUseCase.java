package com.yasiuk.studying.domain.interaction;

import com.yasiuk.studying.data.entity.ScheduleProfile;
import com.yasiuk.studying.data.net.RestService;
import com.yasiuk.studying.domain.entity.Schedule;
import com.yasiuk.studying.domain.interaction.base.UseCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class GetScheduleUseCase extends UseCase<Integer, List<Schedule>> {

    @Override
    protected Observable<List<Schedule>> buildUseCase(Integer integer) {
        return RestService.getInstance().getClassSchedule(integer)
                .map(new Function<List<ScheduleProfile>, List<Schedule>>() {
                    @Override
                    public List<Schedule> apply(@NonNull List<ScheduleProfile> scheduleProfiles) throws Exception {
                        List<Schedule> list = new ArrayList<>();
                        for(ScheduleProfile sp: scheduleProfiles){
                            Schedule schedule = new Schedule();
                            schedule.setClassNumber(sp.getClassNumber());
                            schedule.setDay(sp.getDay());
                            schedule.setTime(sp.getTime());
                            schedule.setSubject(sp.getSubject());
                            list.add(schedule);

                            Collections.sort(list);
                        }
                        return list;
                    }
                });
    }
}
