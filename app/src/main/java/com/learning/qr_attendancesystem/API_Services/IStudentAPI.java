package com.learning.qr_attendancesystem.API_Services;

import com.learning.qr_attendancesystem.models.Students;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IStudentAPI {

    @GET("students")
    Call<List<Students>> getStudents();

    @GET("students/{id}")
    Call<List<Students>> getStudents(@Path("id") int stdId);

    @POST("students")
    Call<Students> createStudent(@Body Students students);
}
