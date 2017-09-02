package com.example.studying.data.net;

//import com.example.studying.data.entity.Profile;

import com.example.studying.data.entity.ContactProfile;
import com.example.studying.data.entity.Profile;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestAPI { //Все методы для доступа к серверу


    @GET("files/{id}")
    Observable<Profile> getProfiles(@Path("id") String name);

    @GET("files/{filename}")
    Observable<ContactProfile> getContact(@Path("filename") String filename);

}
