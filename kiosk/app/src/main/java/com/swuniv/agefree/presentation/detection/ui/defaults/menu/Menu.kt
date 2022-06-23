package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*
* @Parameters
* opt1 : cold, hot
* opt2 : soft, normal, deep
* opt3 : normal, many, very_many
* in_out : in, out
* gender : male, female
* */

/*
* 디저트류 같이 옵션 없는 것들은 '-' default 값 지정
* */

data class Menu(
    @SerializedName("menu")
    var name: String = "",
    @SerializedName("price")
    @Expose(deserialize = false, serialize = false)
    var price: Int = 0,
    @SerializedName("image")
    @Expose(deserialize = false, serialize = false)
    var image: Int?,
    @SerializedName("age")
    var age: Int = 1,
    @SerializedName("gender")
    var gender: String = "M",
    @SerializedName("opt1")
    var option1: String = "-",
    @SerializedName("opt2")
    var option2: String = "-",
    @SerializedName("opt3")
    var option3: String = "-",
    @SerializedName("num")
    var orderCount: Int = 0,
    @SerializedName("in_out")
    var inOut: String = "in"
) : Serializable

data class MenuResponse(
    @SerializedName("success")
    var success: Boolean
)
