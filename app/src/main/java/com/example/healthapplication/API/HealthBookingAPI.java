package com.example.healthapplication.API;

import com.example.healthapplication.Model.HealthBooking;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HealthBookingAPI {
    @POST("healthbooking")
    Call<HealthBooking> registerbooking(@Body HealthBooking healthBooking);
}
