package com.example.fcm_notifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.fcm_notifications.NotificationApi

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun sendNotification(body:String,title:String)= CoroutineScope(Dispatchers.IO).launch {
//        try {
//            //orders is the topic name
//            message = Message("Hello", Message(body, title))
//            val response = NotificationApi.postNotification(message)
//            if (response.isSuccessful) {
//                Log.e("msg1", response.message())
//            } else {
//                //here i get 404
//                Log.e("msg", response.code().toString())
//            }
//        } catch (e: Exception) {
//            Log.e("Error", e.message.toString())
//        }

    }
}