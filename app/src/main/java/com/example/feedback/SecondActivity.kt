package com.example.feedback

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class SecondActivity : AppCompatActivity() {
    companion object {
        const val NAME = "NAME"
        const val FEEDBACK = "FEEDBACK"
        const val EMAIL = "EMAIL"

    }

    private val CHANNEL_ID = "channel_id_1"
    private val notificationId = 101


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val button1: Button = findViewById(R.id.button1)

        val txt1: TextView = findViewById(R.id.textView5)
        val txt2: TextView = findViewById(R.id.textView6)
        val txt3: TextView = findViewById(R.id.textView8)

        val  name = intent.getStringExtra(NAME)
        val email = intent.getStringExtra(EMAIL)
        val feedback = intent.getStringExtra(FEEDBACK)

        txt1.text = "Thank you " + name + " for your feedback"
        txt2.text = "An email will be send to " + email + " regarding your feedback"
        txt3.text = feedback



        createNotificationChannel()

        sendNotification(name)


        button1.setOnClickListener {
            val intent = Intent(this@SecondActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val title = "Feedback"
            val descriptionText = "Your feedback has been submitted successfully"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, title, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }

    private fun sendNotification(name1: String?) {
        val intent = Intent(this, SecondActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        //val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0,intent,0)
        val bitmap =
            BitmapFactory.decodeResource(applicationContext.resources, R.drawable.feedback_img)
        val bitmapLargeIcon =
            BitmapFactory.decodeResource(applicationContext.resources, R.drawable.feedback_img)



        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle("Feedback")
            .setContentText("Thank you "+name1+",  Your feedback has been submitted successfully")
            .setLargeIcon(bitmapLargeIcon)
            //.setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }



    }
}