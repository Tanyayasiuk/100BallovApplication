package com.example.studying.data.net;

import android.util.Log;

//import com.example.studying.data.entity.Profile;
import com.example.studying.data.entity.ContactProfile;
import com.example.studying.data.entity.Profile;
import com.example.studying.data.entity.ScheduleProfile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {

    private static final RestService instance = new RestService();

    private RestAPI restAPI;
    private RestService(){init();}

    public static RestService getInstance(){
        return instance;
    }

    private void init(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();

        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.backendless.com/D98411AD-4158-F507-FF34-CC7C7669CF00/A6BF2F37-9F1B-437A-FF10-82EE9CECF100/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        Log.e("AAA", "RETROFIT");
        restAPI = retrofit.create(RestAPI.class);
    }


   public Observable<Profile> getProfile (String name){
       Log.e("SSS", "Observable<Profile> getProfile");
       String title = name + ".json";
       return restAPI.getProfiles(title);
   }

    public Observable<ContactProfile> getContact (String file){
        String filename = file + ".json";
        return restAPI.getContact(filename);
    }

    public Observable<List<ScheduleProfile>> getSchedule(){
        return restAPI.getProfiles();
    }


    public Observable<List<ScheduleProfile>> getClassSchedule (int classNum){
        String condition = "class="+classNum;
        Log.e("SSS", condition);
        return restAPI.getClassProfiles(condition);
    }



}
