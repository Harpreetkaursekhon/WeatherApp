package com.cloud.myweatherapp.service

import Json4Kotlin_Base
import com.cloud.myweatherapp.model.current.Current
import com.cloud.myweatherapp.model.day.Day
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") key: String,
        @Query("units") units: String
    ): Call<Current>

    @GET("data/2.5/forecast")
    fun get5Day3HourWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") key: String,
        @Query("units") units: String
    ): Call<Day>

    @GET("venues/weather.json")
    fun getVenues(): Call<Json4Kotlin_Base>
}