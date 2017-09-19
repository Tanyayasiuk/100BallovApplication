package com.example.studying.data.net;

import com.example.studying.data.entity.ContactProfile;
import com.example.studying.data.entity.Profile;
import com.example.studying.data.entity.ScheduleProfile;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestAPI { //Все методы для доступа к серверу


    @GET("files/{id}")
    Observable<Profile> getProfiles(@Path("id") String name);

    @GET("files/{filename}")
    Observable<ContactProfile> getContact(@Path("filename") String filename);

    @GET("data/schedule?pageSize=30")
    Observable<List<ScheduleProfile>> getProfiles();

    @GET("data/schedule")
    Observable<List<ScheduleProfile>> getClassProfiles(@Query("where") String condition);

    @GET("data/schedule")
    Observable<List<ScheduleProfile>> getDayProfiles(@Query("where") String condition);
}
