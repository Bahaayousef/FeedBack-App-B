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
import android.widget.EditText
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button:Button = findViewById(R.id.submitbt)
        val name1:EditText= findViewById(R.id.nametxt)
        val email1:EditText= findViewById(R.id.emailtxt)
        val feedback1:EditText= findViewById(R.id.feedbacktxt)

       button.setOnClickListener{
           val name = name1.text.toString()
           val email = email1.text.toString()
           val feedback =feedback1.text.toString()



           val intent = Intent(this@MainActivity,SecondActivity::class.java)
           intent.putExtra(SecondActivity.NAME,name)
           intent.putExtra(SecondActivity.EMAIL,email)
           intent.putExtra(SecondActivity.FEEDBACK,feedback)
           startActivity(intent)
       }
    }
}