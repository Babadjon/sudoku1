package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.view.StartActivity
import java.io.IOException

class Settings : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val mButton = findViewById<View>(R.id.mButton1) as Button
        mButton.setOnClickListener { startActivity(Intent (this@Settings, StartActivity::class.java)) }
    }

    var url:String = "https://ru.drivemusic.me/dl/V9mBKHW-5UErL20ucApIBQ/1675366662/download_music/2014/05/nico-vinz-am-i-wrong.mp3"
    var mediaPlayer = MediaPlayer()

    fun playsong(view: View){
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        if (!mediaPlayer!!.isPlaying){
            Toast.makeText(this, "play", Toast.LENGTH_SHORT).show()
            try {
                mediaPlayer!!.setDataSource(url)
                mediaPlayer.prepare()
                mediaPlayer.start()
            }
            catch (e: IOException){
                e.printStackTrace()
            }
        }
        else {
            Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show()
            try {
                mediaPlayer.pause()
                mediaPlayer.stop()
                mediaPlayer.reset()
            }
            catch (e: IOException){
                e.printStackTrace()
            }
        }
    }


}