package com.example.studying.data.net;

import android.util.Log;

import com.example.studying.data.entity.AccessTokenData;
import com.example.studying.data.entity.ContactProfile;
import com.example.studying.data.entity.Enrollment;
import com.example.studying.data.entity.TeacherProfile;
import com.example.studying.data.entity.StudentLoginData;
import com.example.studying.data.entity.ScheduleProfile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
                .baseUrl("https://api.backendless.com/33732170-B3D2-FFA4-FFD4-AF51778ED800/2A2D1BDE-8799-18BF-FF1E-EB4D95DC0A00/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();


        Log.e("SSS", "RETROFIT");
        restAPI = retrofit.create(RestAPI.class);
    }

   public Observable<TeacherProfile> getProfile (String name){
       Log.e("SSS", "Observable<TeacherProfile> getProfile");
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

    public Observable<AccessTokenData> login(StudentLoginData studentLoginData){

        Log.e("SSS", "RestAPI login method called");

        final Observable<AccessTokenData> token = restAPI.login(studentLoginData)
                .map(new Function<StudentLoginData, AccessTokenData>() {
                    @Override
                    public AccessTokenData apply(@NonNull StudentLoginData loginData) throws Exception {
                        AccessTokenData tokenData = new AccessTokenData();
                        tokenData.setAccessToken(loginData.getToken());
                        Log.e("SSS", loginData.getToken());
                        return tokenData;
                    }
                });
        Log.e("SSS", "почти...");
        return token;
    }

    public Observable<StudentLoginData> createUser(StudentLoginData loginData){
        Log.e("SSS", "attempt to create a new user");
        return restAPI.register(loginData);
    }

    public Observable<Void> enroll(Enrollment enroll){
        Log.e("SSS", "attempt to send email...");
        return restAPI.sendEnrollment(enroll);
    }




}
