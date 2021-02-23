package com.cloud.myweatherapp.model.day

import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all") val all: Int
)