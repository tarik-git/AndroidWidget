package com.example.factsapp.data.remote

import com.example.factsapp.models.Fact
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApiService {

//  http://numbersapi.com/5/23/date?json
    @GET("{month}/{day}/date?json")
    suspend fun getDateFactSuspended(@Path("month") month: Int, @Path("day") day: Int): Fact?

}