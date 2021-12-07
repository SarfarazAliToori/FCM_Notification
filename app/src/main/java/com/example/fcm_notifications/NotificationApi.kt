package com.example.fcm_notifications

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


const val BASE_URL = "https://fcm.googleapis.com"
const val CONTENT_TYPE  ="application/json"
const val SERVER_KEY ="myKey"

interface NotificationApi {

//    @Headers("Authorization: key=$SERVER_KEY", "Content-Type:${CONTENT_TYPE}")
//    @POST("fcm/send")
//    suspend fun postNotification(
//        @Body message: Message
//    ): Response<ResponseBody>
}