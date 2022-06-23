package com.swuniv.agefree.presentation.detection.ui.defaults.menu

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Menu(
    @SerializedName("menu")
    var name: String,
    @SerializedName("price")
    @Expose(deserialize = false, serialize = false)
    var price: Int,
    @SerializedName("image")
    @Expose(deserialize = false, serialize = false)
    var image: Int,
    @SerializedName("age")
    var age: Int,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("opt1")
    var option1: String,
    @SerializedName("opt2")
    var option2: String,
    @SerializedName("opt3")
    var option3: String,
    @SerializedName("num")
    var orderCount: Int,
    @SerializedName("in_out")
    var inOut: String
) : Serializable
