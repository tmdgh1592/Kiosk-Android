package com.swuniv.agefree.presentation.detection.data.network.service

import com.swuniv.agefree.presentation.detection.ui.defaults.menu.BestMenuResponse
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.Menu
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.MenuResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MenuService {

    // 주문 결제 Api
    @POST("/v1/order")
    fun postMenu(@Body menu: Menu): Call<MenuResponse>

    // 사용자 연령대 베스트 메뉴 가져오는 Api
    @GET("/v1/best/{age}/{gender}")
    fun getBestMenu(@Path("age") age: Int, @Path("gender") gender: String): Call<BestMenuResponse>
}