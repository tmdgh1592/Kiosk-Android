package com.swuniv.agefree.presentation.detection.data.network

import com.swuniv.agefree.presentation.detection.data.network.service.FaceDetectService
import com.swuniv.agefree.presentation.detection.data.network.service.MenuService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val AI_BASE_URL = "http://118.67.142.75:5052/"
    private const val MENU_BASE_URL = "http://118.67.142.75:5051/"

    private val aiRetrofit = Retrofit.Builder()
        .baseUrl(AI_BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val menuRetrofit = Retrofit.Builder()
        .baseUrl(MENU_BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val faceDetectApi: FaceDetectService = aiRetrofit.create(FaceDetectService::class.java)
    val menuApi: MenuService = menuRetrofit.create(MenuService::class.java)
}