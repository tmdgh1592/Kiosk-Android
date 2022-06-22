package com.swuniv.agefree.presentation.detection.data.network.service

import com.swuniv.agefree.presentation.detection.data.model.FaceDetectResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FaceDetectService {

    @Multipart
    @POST("/v1/detect")
    fun validateFaceAge(@Part faceImage: MultipartBody.Part): Call<FaceDetectResponse>

}