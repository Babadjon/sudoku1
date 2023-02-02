package com.example.myapplication.view

import android.annotation.SuppressLint
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.Settings
import java.io.IOException

class StartActivity : AppCompatActivity() {


    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val mButton = findViewById<View>(R.id.Btttn) as Button
        mButton.setOnClickListener { startActivity(Intent (this@StartActivity, MainActivity::class.java)) }
        val mButton2 = findViewById<View>(R.id.settings) as Button
        mButton2.setOnClickListener { startActivity(Intent (this@StartActivity, Settings::class.java)) }


    }



}