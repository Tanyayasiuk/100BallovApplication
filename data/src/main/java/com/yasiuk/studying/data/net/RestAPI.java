package com.yasiuk.studying.data.net;

import com.yasiuk.studying.data.entity.BookData;
import com.yasiuk.studying.data.entity.ContactProfile;
import com.yasiuk.studying.data.entity.Enrollment;
import com.yasiuk.studying.data.entity.Message;
import com.yasiuk.studying.data.entity.NewsData;
import com.yasiuk.studying.data.entity.RegisterResponse;
import com.yasiuk.studying.data.entity.StudentLoginData;
import com.yasiuk.studying.data.entity.TaskData;
import com.yasiuk.studying.data.entity.TeacherProfile;
import com.yasiuk.studying.data.entity.ScheduleProfile;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestAPI { //Все методы для доступа к серверу


    @GET("files/{id}")
    Observable<TeacherProfile> getProfiles(@Path("id") String name);

    @GET("files/{filename}")
    Observable<ContactProfile> getContact(@Path("filename") String filename);

    @GET("data/schedule?pageSize=30")
    Observable<List<ScheduleProfile>> getProfiles();

    @GET("data/schedule")
    Observable<List<ScheduleProfile>> getClassProfiles(@Query("where") String condition);

    @GET("data/schedule")
    Observable<List<ScheduleProfile>> getDayProfiles(@Query("where") String condition);

    @POST("users/register")
    Observable<StudentLoginData> register(@Body StudentLoginData data);

    @POST("users/login")
    Observable<StudentLoginData> login(@Body StudentLoginData data);

    @GET("users/logout")
    Observable<Void> logout();

    @PUT("data/user/{id}")
    Observable<Void> editUser (@Path("id") String id, @Body StudentLoginData data);

    @POST("messaging/email")
    Observable<Void> sendEnrollment (@Body Enrollment enroll);

    /*@GET("files/book/{filename}")
    Observable<BookData> getBook(@Path("filename") String filename);
*/
    @GET("files/book/{path}?pageSize=30")
    Observable<List<BookData>> getBook(@Path("path") String path);

    @POST("messaging/news")
    Observable<RegisterResponse> publish (@Body Message message);

    @GET("data/news?pageSize=20&sortBy=newsdate DESC")
    Observable<List<NewsData>> getNews ();

    @POST("data/news")
    Observable<Void> addNews(@Body NewsData newsData);

    @GET("data/hometasks")
    Observable<List<TaskData>> getTask(@Query("where") String condition);
}
