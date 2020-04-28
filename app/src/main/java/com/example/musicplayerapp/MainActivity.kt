package com.example.musicplayerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonPlay.setOnClickListener {
            ForegroundService.startService(this, "Music is playing ...")
        }
     /*   buttonStop.setOnClickListener {
            ForegroundService.stopService(this)
        }*/
    }
}
