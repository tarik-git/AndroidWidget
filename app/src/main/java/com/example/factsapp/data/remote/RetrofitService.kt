package com.example.factsapp.data.remote

import com.example.factsapp.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private val numbersRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL_NUMBERS_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getNumbersApiService(): NumbersApiService {
        return numbersRetrofit.create(NumbersApiService::class.java)
    }

}