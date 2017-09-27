package com.yasiuk.studying.domain.interaction;

import android.util.Log;

import com.yasiuk.studying.data.entity.TeacherProfile;
import com.yasiuk.studying.data.net.RestService;
import com.yasiuk.studying.domain.entity.Teacher;
import com.yasiuk.studying.domain.interaction.base.UseCase;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


/**
 * Gets teacher's profile from database
 */
public class GetTeacherInfoUseCase extends UseCase<String, Teacher> {


    @Override
    protected Observable<Teacher> buildUseCase(String name) {
        Log.e("SSS", "GetTeacherInfoUseCase");
        return RestService.getInstance().getProfile(name).map(new Function<TeacherProfile, Teacher>() {
            @Override
            public Teacher apply(@NonNull TeacherProfile profile) throws Exception {
                Teacher teacher = new Teacher();
                teacher.setName(profile.getName());
                teacher.setSurname(profile.getSurname());
                teacher.setEducation(profile.getEducation());
                teacher.setAdditional(profile.getAdditional());
                teacher.setLessontype(profile.getLessontype());
                teacher.setClasses(profile.getClasses());
                teacher.setComment(profile.getComment());
                teacher.setImageUrl(profile.getImageUrl());
                Log.e("SSS", "getting Teacher ");
                return teacher;
            }
        });
    }
}
