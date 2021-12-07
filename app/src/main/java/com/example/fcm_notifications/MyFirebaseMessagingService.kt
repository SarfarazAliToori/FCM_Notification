package com.example.fcm_notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.text.TextUtils
import com.google.android.gms.tasks.*

import com.google.firebase.messaging.FirebaseMessaging
import java.lang.Exception


const val channelId: String = "notification_channel_id"
const val notiticationName: String = "fcm_notification"
const val TAG: String = "Hello"

class MyFirebaseMessagingService : FirebaseMessagingService() {

    fun generateNotification(title: String, message: String) {

        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        var builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.ic_notifications)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
            .setContentIntent(pendingIntent)

        builder = builder.setContent(getRemoteView(title, message))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(channelId, notiticationName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(0, builder.build())

    }

    private fun getRemoteView(title: String, message: String): RemoteViews? {
        val remoteViews = RemoteViews("com.example.fcm_notifications",R.layout.notification_design)
        remoteViews.setTextViewText(R.id.tv_title, title)
        remoteViews.setTextViewText(R.id.tv_message, message)
        remoteViews.setImageViewResource(R.id.notification_icon, R.drawable.ic_notifications)
        return remoteViews
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification != null) {
            generateNotification(remoteMessage.notification!!.title!!, remoteMessage.notification!!.body!!)
            Log.d("hh", "From: ${remoteMessage.from}")
            Log.d("hh", "Message data payload: ${remoteMessage.data}")
        }
        ///

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
//        Log.d(TAG, "From: ${remoteMessage?.from}")
//
//        // TODO: Step 3.5 check messages for data
//        // Check if the message contains a data payload.
//        remoteMessage?.data?.let {
//            Log.d(TAG, "Message data payload: " + remoteMessage.data)
//        }
//
//        // TODO: Step 3.6 check messages for notification and call sendNotification
//        // Check if the message contains a notification payload.
//        remoteMessage.notification?.let {
//            Log.d(TAG, "Message Notification Body: ${it.body}")
//            //sendNotification(it.body as String)
//        }



         /////////// Token ///////////
        FirebaseMessaging.getInstance().token.addOnSuccessListener { token: String ->
            if (!TextUtils.isEmpty(token)) {
                Log.d("hello", "token successfully retrieved : $token")
            } else {
                Log.w("hello", "token should not be null...")
            }
        }.addOnFailureListener { e: Exception? -> }.addOnCanceledListener {}
            .addOnCompleteListener { task: Task<String> ->
                Log.v(
                    TAG,
                    "This is the token : " + task.result
                )
            }


        //////////
    }
}
