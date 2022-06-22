package com.swuniv.agefree.presentation.detection.data.network

import com.swuniv.agefree.presentation.detection.data.network.service.FaceDetectService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "http://118.67.142.75:5052/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val faceDetectApi: FaceDetectService = retrofit.create(FaceDetectService::class.java)
}