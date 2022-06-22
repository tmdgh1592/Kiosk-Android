package com.swuniv.agefree.presentation.detection.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FaceDetectResponse(
    @Expose
    @SerializedName("success")
    val success: Boolean,
    @Expose
    @SerializedName("age")
    val age: Int,
    @Expose
    @SerializedName("gender")
    val gender: String
)

//data class FaceDetectRequest(
//    @SerializedName("success")
//    val success: Boolean,
//    @SerializedName("age")
//    val age: Int,
//    @SerializedName("gender")
//    val gender: String
//)

