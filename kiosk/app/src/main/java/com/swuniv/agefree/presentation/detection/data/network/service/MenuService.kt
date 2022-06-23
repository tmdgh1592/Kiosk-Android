package com.swuniv.agefree.presentation.detection.data.network.service

import com.swuniv.agefree.presentation.detection.ui.defaults.menu.Menu
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.MenuResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MenuService {

    @POST("/v1/order")
    fun postMenu(@Body menu: Menu): Call<MenuResponse>

}